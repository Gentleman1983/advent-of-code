package de.havox_design.aoc2024.day12

class Day12(private var filename: String) {
    fun solvePart1(): Long =
        0L

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}