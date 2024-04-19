package de.havox_design.aoc2020.day01

class RepairReport(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val numbers = data
            .map { it.toLong() }
            .toSet()

        return findParts(numbers, 2020L) ?: throw IllegalStateException("No solution found")
    }

    fun processPart2(): Any {
        val numbers = data
            .map { it.toLong() }
            .toMutableSet()

        for (first in numbers) {
            val parts = findParts(numbers, 2020L - first)

            if (parts != null) {
                return parts * first
            }
        }

        throw IllegalStateException("No solution found")
    }

    private fun findParts(numbers: Set<Long>, requiredSum: Long): Long? {
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
