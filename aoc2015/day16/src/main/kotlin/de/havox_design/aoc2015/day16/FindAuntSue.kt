package de.havox_design.aoc2015.day16

import java.util.stream.Collectors

class LogicGates(private var filename: String) {
    private val data = readData()
    private val signature = AuntSueSignature

    fun processPart1(): Int =
        data
            .toMutableSet()
            .apply { removeIf{!signature.matchesButOutdated(it)} }
            .single()
            .id

    fun processPart2(): Int =
        data
            .toMutableSet()
            .apply { removeIf{ !signature.matchesWithRanges(it)} }
            .single()
            .id

    private fun readData(): Set<AuntSueDescription> =
        getResourceAsText(filename)
            .stream()
            .map(AuntSueDescription::from)
            .collect(Collectors.toSet())

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    private object AuntSueSignature {
        private val properties: Map<String, Int> = """
             children: 3
             cats: 7
             samoyeds: 2
             pomeranians: 3
             akitas: 0
             vizslas: 0
             goldfish: 5
             trees: 3
             cars: 2
             perfumes: 1
             """.trimIndent().lines()
            .associate { it.substringBefore(": ") to it.substringAfter(": ").toInt() }

        fun matchesButOutdated(description: AuntSueDescription): Boolean =
            description.properties.all { (key, value) ->
                properties.containsKey(key) && properties[key] == value
            }

        @SuppressWarnings("kotlin:S6611")
        fun matchesWithRanges(description: AuntSueDescription) =
            description.properties.all { (key, value) ->
                properties.containsKey(key) &&
                        when (key) {
                            "cats", "trees" -> properties[key]!! < value
                            "pomeranians", "goldfish" -> properties[key]!! > value
                            else -> properties[key] == value
                        }
            }
    }
}
