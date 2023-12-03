package de.havox_design.aoc2023.day03

class Day03(private var filename: String) {
    fun solvePart1(): Long =
        EngineSchematic(getResourceAsText(filename))
            .sumOfPartNumbers()

    fun solvePart2(): Long =
        467835L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}