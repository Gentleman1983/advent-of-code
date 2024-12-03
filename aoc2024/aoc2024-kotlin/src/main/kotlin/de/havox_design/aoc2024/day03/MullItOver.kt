package de.havox_design.aoc2024.day03

class MullItOver(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val multiplicationRegex = Regex("""mul\((\d+),(\d+)\)""")
    private val operationRegex = Regex("""do\(\)|don't\(\)|mul\((\d+),(\d+)\)""")

    fun processPart1(): Any =
        multiplicationRegex
            .findAll(data)
            .sumOf { it.multiplyArguments() }

    fun processPart2(): Any {
        var enabled = true
        var currentSum = 0

        for (match in operationRegex.findAll(data)) {
            when (match.value) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> if (enabled) {
                    currentSum += match.multiplyArguments()
                }
            }
        }

        return currentSum
    }

    private fun MatchResult.multiplyArguments(): Int =
        groupValues[1].toInt() * groupValues[2].toInt()

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
            .joinToString("")
}
