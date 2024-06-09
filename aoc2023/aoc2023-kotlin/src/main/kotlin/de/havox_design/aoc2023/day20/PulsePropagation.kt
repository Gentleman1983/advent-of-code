package de.havox_design.aoc2023.day20

import de.havox_design.aoc.utils.java.helper.JavaMathUtils.leastCommonMultiple
import de.havox_design.aoc2023.day20.Module.Companion.ICON_BROADCASTER
import de.havox_design.aoc2023.day20.Module.Companion.ICON_BUTTON
import de.havox_design.aoc2023.day20.Module.Companion.ICON_CONJUCTION
import de.havox_design.aoc2023.day20.Module.Companion.ICON_FLIP_FLOP

class PulsePropagation(private var filename: String) {
    private val SIGNAL_DELIMITER = " -> "
    private val OUTPUTS_DELIMITER = ", "

    fun solvePart1(runs: Int = 1000): Long {
        val (modules, assignment, conjunctionInputs) = parseInput()

        var count = 0
        var currentAssignment = assignment
            .toMutableMap()
        var currentConjugation = conjunctionInputs
        var highCounter = 0L
        var lowCounter = 0L

        do {
            val (thisAssignment, conjugation) = modules
                .pressTheButton(currentAssignment, currentConjugation) { pulse, _ ->
                    when (pulse) {
                        Pulse.HIGH -> highCounter++
                        else -> lowCounter++
                    }
                }

            count++
            currentConjugation = conjugation
            currentAssignment = thisAssignment
        } while (count < runs)

        return highCounter * lowCounter
    }

    @SuppressWarnings("kotlin:S3776", "kotlin:S6611")
    fun solvePart2(): Long {
        val parentName = "&lg"
        val modules = HashMap<String, ArrayList<String>>()
        var on = HashMap<String, Boolean>()
        var conjunctions = HashMap<String, HashMap<String, Boolean>>()

        for (row in getResourceAsText(filename)) {
            val name = row.split(SIGNAL_DELIMITER)[0]
            modules[name] = ArrayList(
                row
                    .split(SIGNAL_DELIMITER)[1]
                    .split(OUTPUTS_DELIMITER)
                    .map { it.trim() }
                    .toList()
            )

            when {
                name.startsWith(ICON_FLIP_FLOP) -> on[name] = false
            }
        }

        for (module in modules) {
            for (i in 0..<module.value.size) {
                when {
                    modules.contains(ICON_FLIP_FLOP + module.value[i]) -> module.value[i] =
                        ICON_FLIP_FLOP + module.value[i]

                    modules.contains(ICON_CONJUCTION + module.value[i]) -> module.value[i] =
                        ICON_CONJUCTION + module.value[i]
                }
            }
        }

        for (module in modules) {
            when {
                module.key.startsWith(ICON_CONJUCTION) -> conjunctions[module.key] = HashMap()
            }
        }

        for (module in modules) {
            for (output in module.value) {
                when {
                    conjunctions.contains(output) -> conjunctions[output]!![module.key] = false
                }
            }
        }

        val pulses = ArrayDeque<Pair<Boolean, String>>()
        val sent = HashMap<Boolean, Long>()

        sent[false] = 0
        sent[true] = 0

        fun send(from: String, to: String, value: Boolean) {
            if (conjunctions.contains(to)) conjunctions[to]!![from] = value
            pulses.add(Pair(value, to))
            sent[value] = sent[value]!! + 1
        }

        for (i in 1..1000) {
            send(ICON_BUTTON, ICON_BROADCASTER, false)
            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                val value = pulse.first
                val to = pulse.second

                when {
                    !modules.contains(to) -> continue
                }

                when {
                    to == ICON_BROADCASTER -> for (output in modules[to]!!) {
                        send(to, output, value)
                    }

                    to.startsWith(ICON_FLIP_FLOP) -> when {
                        !value -> {
                            on[to] = !on[to]!!
                            for (output in modules[to]!!) {
                                send(to, output, on[to]!!)
                            }
                        }
                    }

                    to.startsWith(ICON_CONJUCTION) -> {
                        for (output in modules[to]!!) {
                            send(to, output, !conjunctions[to]!!.all { it.value })
                        }
                    }
                }
            }
        }

        on = HashMap(
            on
                .map { Pair(it.key, false) }
                .toMap()
        )
        conjunctions = HashMap(
            conjunctions
                .map { Pair(it.key, HashMap(it.value.map { dest -> Pair(dest.key, false) }.toMap())) }
                .toMap()
        )

        val cycles = HashMap<String, Long>()
        var buttonCounter = 1

        while (true) {
            send(ICON_BUTTON, ICON_BROADCASTER, false)

            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                val to = pulse.second
                val value = pulse.first

                when {
                    conjunctions[parentName]!!.contains(to) -> {
                        when {
                            !cycles.contains(to) && conjunctions[parentName]!![to]!! -> {
                                cycles[to] = buttonCounter.toLong()
                            }
                        }

                        when (cycles.keys) {
                            conjunctions[parentName]!!.keys -> {
                                return cycles.values.reduce { acc, c -> leastCommonMultiple(acc, c) }
                            }
                        }
                    }
                }

                when {
                    !modules.contains(to) -> continue
                }

                when {
                    to == ICON_BROADCASTER -> for (output in modules[to]!!) {
                        send(to, output, value)
                    }

                    to[0] == ICON_FLIP_FLOP.first() -> when {
                        !value -> {
                            on[to] = !on[to]!!
                            for (output in modules[to]!!) {
                                send(to, output, on[to]!!)
                            }
                        }
                    }

                    to[0] == ICON_CONJUCTION.first() -> for (output in modules[to]!!) {
                        send(to, output, !conjunctions[to]!!.all { it.value })
                    }
                }

            }

            buttonCounter++
        }
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
