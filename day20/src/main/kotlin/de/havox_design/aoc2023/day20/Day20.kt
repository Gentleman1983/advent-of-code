package de.havox_design.aoc2023.day20

class Day20(private var filename: String) {
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

    fun solvePart2(): Long =
        0L

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
        val queue = mutableListOf(Triple("button", Pulse.LOW, "broadcaster"))

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

    private fun parseInput(): Triple<Map<String, Module>, Map<String, Boolean>, Map<String, MutableMap<String, Boolean>>> {
        val modules = getResourceAsText(filename)
            .associate { line ->
                val (name, connections) = line.split(" -> ", limit = 2)
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
