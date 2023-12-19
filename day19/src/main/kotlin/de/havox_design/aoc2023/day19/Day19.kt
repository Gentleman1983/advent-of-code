package de.havox_design.aoc2023.day19

class Day19(private var filename: String) {
    fun solvePart1(): Long =
        19114L

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}