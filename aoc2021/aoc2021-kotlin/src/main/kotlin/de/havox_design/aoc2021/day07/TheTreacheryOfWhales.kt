package de.havox_design.aoc2021.day07

import kotlin.math.abs

class TheTreacheryOfWhales(private var filename: String) {
    private val VALUE_DELIMITER = ","
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val positions = data
            .split(VALUE_DELIMITER)
            .map { it.toInt() }
        val (minPos, maxPos) = positions.minAndMax()

        return (minPos..maxPos).minOf { calculateFuelPart1(it, positions) }
    }

    fun processPart2(): Any {
        val positions = data
            .split(VALUE_DELIMITER)
            .map { it.toInt() }
        val (minPos, maxPos) = positions.minAndMax()

        return (minPos..maxPos).minOf { calculateFuelPart2(it, positions) }
    }

    private fun calculateFuelPart1(target: Int, positions: List<Int>): Int =
        positions
            .sumOf { abs(target - it) }

    private fun calculateFuelPart2(target: Int, positions: List<Int>): Int = positions.sumOf {
        val abs = abs(target - it)

        abs * (abs + 1) / 2
    }

    @SuppressWarnings("kotlin:S6532")
    private fun <T : Comparable<T>> Iterable<T>.minAndMax(): Pair<T, T> {
        val iterator = iterator()
        if (!iterator.hasNext()) {
            throw IllegalArgumentException("Cannot get min and max of empty collection")
        }

        var min = iterator.next()
        var max = min

        while (iterator.hasNext()) {
            val next = iterator.next()

            when {
                next < min -> min = next
                next > max -> max = next
            }
        }

        return min to max
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
