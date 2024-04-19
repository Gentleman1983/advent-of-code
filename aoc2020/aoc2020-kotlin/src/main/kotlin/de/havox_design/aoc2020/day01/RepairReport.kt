package de.havox_design.aoc2020.day01

class RepairReport(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val numbers = data
            .map { it.toLong() }
            .toSet()

        return findParts(numbers, 2020) ?: throw IllegalStateException("No solution found")
    }

    fun processPart2(): Any =
        0L

    private fun findParts(numbers: Set<Long>, requiredSum: Int): Long? {
        for (first in numbers) {
            val second = requiredSum - first

            if (second in numbers) {
                return first * second
            }
        }

        return null
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
