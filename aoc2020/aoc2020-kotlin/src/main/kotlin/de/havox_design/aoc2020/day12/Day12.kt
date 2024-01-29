package de.havox_design.aoc2020.day12

class Day12(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        0L

    fun processPart2(): Any =
        0L
    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
