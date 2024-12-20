package de.havox_design.aoc2024.day20

class RaceCondition(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(minimumCostSaving: Int): Any =
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
