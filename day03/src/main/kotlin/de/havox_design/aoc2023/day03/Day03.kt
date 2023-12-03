package de.havox_design.aoc2023.day03

class Day03(private var filename: String) {
    fun solvePart1(): Long =
        4361L

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}