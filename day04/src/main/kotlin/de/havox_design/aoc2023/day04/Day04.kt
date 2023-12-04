package de.havox_design.aoc2023.day04

class Day04(private var filename: String) {
    fun solvePart1(): Long =
        getResourceAsText(filename)
            .filter(String::isNotBlank)
            .map { ScratchCard.from(it) }
            .sumOf { it.scoreV1() }

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}