package de.havox_design.aoc2024.day11

class Day11(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        0L

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
