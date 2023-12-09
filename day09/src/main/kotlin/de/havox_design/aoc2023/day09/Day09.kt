package de.havox_design.aoc2023.day09

class Day09(private var filename: String) {
    fun solvePart1(): Long =
        convertInput()
            .sumOf { history ->
            processOasisReport(history)
        }

    fun solvePart2(): Long =
        0L

    private fun processOasisReport(oasisHistory: List<Long>): Long {
        var value = 0L
        var factor = 1
        var current = oasisHistory

        while (current.any { it != 0L }) {
            value += current.last()

            current = current.windowed(2).map { (a, b) -> b - a }
        }

        return value
    }

    private fun convertInput(): List<List<Long>> =
        getResourceAsText(filename)
            .map { row ->
                row
                    .split(" ")
                    .stream()
                    .map { element -> element.toLong() }
                    .toList()
            }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}