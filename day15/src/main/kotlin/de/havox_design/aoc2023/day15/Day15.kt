package de.havox_design.aoc2023.day15

class Day15(private var filename: String) {
    private val ELEMENT_DELIMITER = ","

    fun solvePart1(): Long =
        getResourceAsText(filename)[0]
            .split(ELEMENT_DELIMITER)
            .sumOf { word -> calculateHash(word) }
            .toLong()

    fun solvePart2(): Long =
        0L

    private fun calculateHash(word: String): Int =
        word
            .fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}