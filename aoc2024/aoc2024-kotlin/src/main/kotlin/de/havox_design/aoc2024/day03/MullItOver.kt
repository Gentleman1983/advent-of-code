package de.havox_design.aoc2024.day03

class MullItOver(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val multiplicationRegex = Regex("""mul\((\d+),(\d+)\)""")

    fun processPart1(): Any =
        multiplicationRegex
            .findAll(data.joinToString(""))
            .sumOf { it.multiplyArguments() }

    fun processPart2(): Any =
        0L

    private fun MatchResult.multiplyArguments(): Int =
        groupValues[1].toInt() * groupValues[2].toInt()

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
