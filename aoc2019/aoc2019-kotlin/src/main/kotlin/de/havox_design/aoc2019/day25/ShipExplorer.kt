package de.havox_design.aoc2019.day25

import de.havox_design.aoc.utils.kotlin.helpers.drainToList
import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.LinkedBlockingQueue

private const val securityCheckpointName = "== Security Checkpoint =="

class ShipExplorer(private val program: List<Long>) {
    private val untakeableItems = mutableSetOf("infinite loop", "giant electromagnet")
    private val allItems = mutableListOf<String>()
    private val rooms = mutableMapOf<String, Room>()
    private var pathToSecurityCheckpoint: List<GeoDirection>? = null
    private var code = ""

    @SuppressWarnings("kotlin:S1481")
    private fun handleOutput(output: String, pathState: PathState): String {
        pathState.lastItem = null

        val trimmedOutput = output.trim()

        when {
            trimmedOutput.contains("A loud, robotic voice says") -> {
                throw IllegalStateException("Passed Security Checkpoint")
            }

            trimmedOutput.contains("==") -> {
                val intialOutputLines = trimmedOutput.lines()
                val outputLines = intialOutputLines.run { subList(indexOfLast { it.startsWith("==") }, size) }
                val roomName = outputLines[0]
                val doors = outputLines
                    .sectionFor("Doors here lead:")
                    .map {
                        it
                            .trim(' ', '-')
                            .uppercase(Locale.getDefault())
                    }
                    .map { GeoDirection.valueOf(it) }
                    .toSet()
                val items = outputLines
                    .sectionFor("Items here:")
                    .map { it.trim(' ', '-') }
                    .toSet()
                val currentRoom = rooms
                    .getOrPut(roomName) {
                        Room(roomName, doors)
                    }
                val previousRoom = pathState.currentRoom

                if (previousRoom != null && pathState.currentPath.isNotEmpty()) {
                    val lastStep = pathState.currentPath.peek()

                    previousRoom.neighbours[lastStep] = currentRoom
                    currentRoom.neighbours[lastStep.opposite] = previousRoom
                }

                if (roomName == securityCheckpointName) {
                    pathToSecurityCheckpoint = pathState.currentPath.toList()

                    return backtrack(pathState)
                }

                pathState.currentRoom = currentRoom

                val takableItems = items.filter { it !in untakeableItems }

                check(takableItems.size <= 1) { "Cannot handle more than one item yet" }
                if (takableItems.isNotEmpty()) {
                    allItems.addAll(takableItems)
                    pathState.lastItem = takableItems[0]

                    return "take ${takableItems[0]}"
                }

                return issueMoveCommand(pathState)
            }

            trimmedOutput.startsWith("You take the") -> {
                return issueMoveCommand(pathState)
            }

            else -> {
                println("Unable to handle output: \n******\n$output\n******\n")
                throw IllegalArgumentException("Unable to handle output.")
            }
        }
    }

    private fun backtrack(pathState: PathState): String {
        val last = pathState
            .currentPath
            .pop()

        return last
            .opposite
            .name
            .lowercase(Locale.getDefault())
    }

    private fun issueMoveCommand(pathState: PathState): String {
        val unknownNeighbours = pathState
            .currentRoom!!
            .unknownNeighbours()

        return if (unknownNeighbours.isNotEmpty()) {
            val direction = unknownNeighbours.first()

            pathState.currentPath.push(direction)
            direction.name.lowercase(Locale.getDefault())
        } else {
            backtrack(pathState)
        }
    }

    private val unexploredRooms: Boolean
        get() =
            rooms.isEmpty() || rooms.values.any { it.hasUnknownNeighbours() && it.name != securityCheckpointName }

    fun solveA(): Any {
        var previousIteration: PathState? = null

        while (unexploredRooms) {
            val pathState = PathState()

            exploreRooms(pathState, previousIteration)
            previousIteration = pathState
        }

        solveSecurityCheck(previousIteration!!)

        return code
    }

    private fun solveSecurityCheck(previousIteration: PathState) {
        val backtrack = previousIteration
            .currentPath
            .reversed()
            .map { it.opposite.name.lowercase(Locale.getDefault()) }
        val securityPath = pathToSecurityCheckpoint!!
            .map { it.name.lowercase(Locale.getDefault()) }
        val initialCommands = previousIteration.commandsHistory + backtrack + securityPath
        val inputNotifier = LinkedBlockingQueue<Boolean>()
        val intCode = buildIntCode(inputNotifier, initialCommands)
        val securityRoom = rooms
            .getValue(securityCheckpointName)
        val inventory = checkInventory(intCode)
        val dropAll = inventory
            .map { "drop $it" }
        val takeAll = inventory
            .map { "take $it" }
        val sentCombinations = mutableSetOf<Set<String>>()
        val combinationsToExpand = mutableSetOf(emptySet<String>())
        val directionCommand = securityRoom
            .unknownNeighbours()
            .first()
            .name
            .lowercase(Locale.getDefault())

        do {
            val combination = combinationsToExpand.first()

            combinationsToExpand.remove(combination)

            if (sentCombinations.add(combination)) {
                intCode.sendAscii(dropAll)
                intCode.sendAscii(combination)
                intCode.sendAscii("inv")
                intCode.sendAscii(directionCommand)

                val add = takeAll - combination
                val newCombinations = add.map { combination + it }

                combinationsToExpand.addAll(newCombinations)
            } else {
                println("Already Sent $combination")
            }
        } while (combinationsToExpand.isNotEmpty())

        intCode.runUtilInput()

        val output = intCode.readOutputString()
        val keypadText = "Oh, hello! You should be able to get in by typing"

        if (output.contains(keypadText)) {
            code = output
                .lines()
                .first { line -> line.contains(keypadText) }
                .filter { it.isDigit() }
        }
    }

    private fun checkInventory(intCode: IntCode): Set<String> {
        intCode.sendAscii("inv")
        intCode.runUtilInput()

        val outputs = intCode
            .readOutputString()
            .lines()

        return outputs
            .sectionFor("Items in your inventory:")
            .map { it.trim(' ', '-') }
            .toSet()
    }

    private fun List<String>.sectionFor(line: String): List<String> {
        val startIndex = indexOfFirst { it == line }

        if (startIndex == -1) {
            return emptyList()
        }

        val endIndex = startIndex + subList(startIndex, size).indexOfFirst { it.isBlank() }

        return subList(startIndex + 1, endIndex)
    }

    private fun exploreRooms(pathState: PathState, previousIteration: PathState?) {
        val initialCommands = commandHistoryWithoutLast(previousIteration)

        pathState.commandsHistory.addAll(initialCommands)
        pathState.currentPath.addAll(previousIteration?.currentPath ?: emptyList())

        val notifierQueue = LinkedBlockingQueue<Boolean>()
        val intCode = buildIntCode(notifierQueue, initialCommands)
        val intcodeFuture = CompletableFuture
            .runAsync {
                try {
                    intCode.runProgram()
                } finally {
                    notifierQueue.add(false)
                }
            }
        val exploringFuture = CompletableFuture
            .runAsync {
                while (unexploredRooms && notifierQueue.take()) {
                    val outputString = intCode.readOutputString()
                    val command = handleOutput(outputString, pathState)

                    pathState.commandsHistory.add(command)
                    intCode.sendAscii(command)
                }

                if (intCode.running) {
                    pathState.commandsHistory.add("Cancel")
                    intcodeFuture.cancel(true)
                } else {
                    val lastItem = pathState.lastItem

                    if (lastItem != null) {
                        untakeableItems.add(lastItem)
                    }
                }
            }

        try {
            CompletableFuture.allOf(exploringFuture, intcodeFuture).get()
        } catch (e: ExecutionException) {
            if (e.cause == null) {
                throw IllegalStateException(e)
            } else if (e.cause !is CancellationException) {
                throw IllegalStateException(e.cause!!)
            }
        }
    }

    private fun IntCode.readOutputString(): String {
        val outputs = outputs.drainToList()

        return outputs
            .joinToString(separator = "") { it.toInt().toChar().toString() }
    }

    private fun buildIntCode(
        notifierQueue: LinkedBlockingQueue<Boolean>,
        initialCommands: List<String>
    ): IntCode {
        val intCode = IntCode(program) {
            notifierQueue.put(true)
        }

        intCode.sendAscii(initialCommands)

        return intCode
    }

    private fun commandHistoryWithoutLast(previousIteration: PathState?): List<String> {
        return previousIteration
            ?.commandsHistory
            ?.run {
                subList(0, lastIndex)
            } ?: emptyList()
    }
}
