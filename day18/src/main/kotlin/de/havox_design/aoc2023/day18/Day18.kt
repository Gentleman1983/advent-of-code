package de.havox_design.aoc2023.day18

class Day18(private var filename: String) {
    fun solvePart1(): Long =
        62L

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}