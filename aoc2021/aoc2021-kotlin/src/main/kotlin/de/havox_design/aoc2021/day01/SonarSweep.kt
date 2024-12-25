package de.havox_design.aoc2021.day01

class SonarSweep(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .map { line -> line.trim().toInt() }
            .zipWithNext()
            .count { (a, b) -> b > a }

    fun processPart2(): Any =
        data
            .asSequence()
            .map { line -> line.trim().toInt() }
            .windowed(3)
            .map { it.sum() }
            .zipWithNext()
            .count { (a, b) -> b > a }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
