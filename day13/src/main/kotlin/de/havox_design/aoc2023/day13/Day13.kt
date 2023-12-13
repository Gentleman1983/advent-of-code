package de.havox_design.aoc2023.day13

class Day13(private var filename: String) {
    fun solvePart1(): Long =
        405L

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}