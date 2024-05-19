package de.havox_design.aoc2021.day01

class Day01(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        getResourceAsText(filename)
            .map{ line -> line.trim().toInt()}
            .zipWithNext()
            .count { (a, b) -> b > a }

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
