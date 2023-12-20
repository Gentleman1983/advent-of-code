package de.havox_design.aoc2023.day20

import de.havox_design.aoc2023.day20.Module.Companion.ICON_BROADCASTER
import de.havox_design.aoc2023.day20.Module.Companion.ICON_BUTTON
import de.havox_design.aoc2023.day20.Module.Companion.ICON_RECEIVER
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day20(private var filename: String) {
    private val SIGNAL_DELIMITER = " -> "

    fun solvePart1(runs: Int = 1000): Long {
        val (modules, assignment, conjunctionInputs) = parseInput()

        var count = 0
        var currentAssignment = assignment
            .toMutableMap()
        var currentConjugation = conjunctionInputs
        var highCounter = 0L
        var lowCounter = 0L

        do {
            val (assignment, conjugation) = modules
                .pressTheButton(currentAssignment, currentConjugation) { pulse, _ ->
                    when (pulse) {
                        Pulse.HIGH -> highCounter++
                        else -> lowCounter++
                    }
                }

            count++
            currentConjugation = conjugation
            currentAssignment = assignment
        } while (count < runs)

        return highCounter * lowCounter
    }

    @SuppressWarnings("kotlin:S3776", "kotlin:S6611")
    fun solvePart2(): Long {
        val (modules, assignment, conjunctionInputs) = parseInput()
        val parent = modules
            .filter { it.value.connections.contains(ICON_RECEIVER) }
            .values
            .first()
        val grandParents = modules
            .filter { it.value.connections.contains(parent.name) }
            .keys
            .toMutableSet()
        val cycles = mutableListOf<Long>()
        var count = 1L
        val countMap = grandParents
            .associateWith { 0L }
            .toMutableMap()
        val cycle = mutableMapOf<String, Long>()
        var currentAssignment = assignment
        var currentConjugation: Map<String, Map<String, Boolean>> = conjunctionInputs

        while (grandParents.isNotEmpty()) {
            var index = 0
            val nextState = currentAssignment
                .toMutableMap()
            val nextConjunctionInputs = currentConjugation
                .mapValues { it.value.toMutableMap() }
            val queue = mutableListOf(Triple(ICON_BUTTON, Pulse.LOW, ICON_BROADCASTER))

            while (index < queue.size) {
                val (from, pulse, target) = queue[index]
                val isHigh = pulse == Pulse.HIGH
                val current = modules[target]

                when {
                    target in grandParents && !isHigh -> {
                        countMap[target] = countMap[target]!! + 1

                        when {
                            countMap[target] == 2L -> {
                                grandParents.remove(target)
                                cycles.add(count - cycle[target]!!)
                            }
                        }

                        cycle[target] = count
                    }
                }

                when (current) {
                    null -> {
                        nextState[target] = isHigh
                        index++
                        continue
                    }
                }

                nextConjunctionInputs[target]
                    ?.set(from, isHigh)
                val inputs = nextConjunctionInputs[target] ?: emptyMap()
                val output = ModuleType.processSignal(current!!.type, isHigh, nextState[target] ?: false, inputs)

                when {
                    output != Pulse.NONE -> {
                        nextState[target] = output == Pulse.HIGH
                        current
                            .connections
                            .map { Triple(target, output, it) }
                            .toCollection(queue)
                    }
                }

                index++
            }

            count++
            currentConjugation = nextConjunctionInputs
            currentAssignment = nextState
        }

        return (cycles.lcmForList())
    }

    private fun Map<String, Module>.pressTheButton(
        currentState: Map<String, Boolean>,
        conjunctionInputs: Map<String, Map<String, Boolean>>,
        body: (Pulse, String) -> Unit
    ): Pair<MutableMap<String, Boolean>, Map<String, MutableMap<String, Boolean>>> {
        var index = 0
        val nextState = currentState
            .toMutableMap()
        val nextConjunctionInputs = conjunctionInputs
            .mapValues { it.value.toMutableMap() }
        val queue = mutableListOf(Triple(ICON_BUTTON, Pulse.LOW, ICON_BROADCASTER))

        while (index < queue.size) {
            val (from, pulse, target) = queue[index]
            val isHigh = pulse == Pulse.HIGH
            body(pulse, target)
            val current = get(target)

            when (current) {
                null -> {
                    nextState[target] = isHigh
                    index++
                    continue
                }
            }

            nextConjunctionInputs[target]
                ?.set(from, isHigh)

            val inputs = nextConjunctionInputs[target] ?: emptyMap()
            val output = ModuleType.processSignal(current!!.type, isHigh, nextState[target] ?: false, inputs)

            when {
                output != Pulse.NONE -> {
                    nextState[target] = output == Pulse.HIGH
                    current
                        .connections
                        .map { Triple(target, output, it) }
                        .toCollection(queue)
                }
            }

            index++
        }

        return nextState to nextConjunctionInputs
    }

    private fun List<Long>.lcmForList(): Long =
        fold(1L) { lcmOfAllNumbers, number ->
            return when (val result = lcm(lcmOfAllNumbers, number)) {
                0L -> 0
                else -> result
            }
        }

    private fun lcm(number1: Long, number2: Long): Long =
        when {
            number1 == 0L || number2 == 0L -> 0
            else -> {
                val absHigherNumber = absoluteMax(number1, number2)
                val absLowerNumber = absoluteMin(number1, number2)
                var lcm = absHigherNumber

                while (lcm % absLowerNumber != 0L) {
                    lcm += absHigherNumber
                }

                lcm
            }
        }

    private fun absoluteMax(num1: Long, num2: Long) =
        max(abs(num1), abs(num2))

    private fun absoluteMin(num1: Long, num2: Long) =
        min(abs(num1), abs(num2))

    private fun parseInput(): Triple<Map<String, Module>, Map<String, Boolean>, Map<String, MutableMap<String, Boolean>>> {
        val modules = getResourceAsText(filename)
            .associate { line ->
                val (name, connections) = line.split(SIGNAL_DELIMITER, limit = 2)
                val (type, realName) = ModuleType.parseModule(name)
                realName to Module(realName, type, connections.split(", "))
            }

        val assignment = emptyMap<String, Boolean>()
        val conjunctionInputs = modules
            .filter { it.value.type == ModuleType.CONJUNCTION }
            .map { it.key }.associateWith { name ->
                modules
                    .filter { it.value.connections.contains(name) }
                    .map { it.key to (it.value.type == ModuleType.CONJUNCTION) }
                    .toMap()
                    .toMutableMap()
            }

        return Triple(modules, assignment, conjunctionInputs)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
