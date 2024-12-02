package de.havox_design.aoc2024.day02

import kotlin.math.abs
import kotlin.math.sign

class RedNosedReports(private var filename: String) {
    private val data = convertData(getResourceAsText(filename))

    fun processPart1(): Any =
        data
            .count {
                it
                    .isSafe()
            }

    fun processPart2(): Any =
        data
            .count { line ->
                val unsafeJumpIndex = line
                    .indexOfUnsafeJump()
                var isSafe = unsafeJumpIndex == NOT_FOUND
                var indexToRemove = (unsafeJumpIndex - 1)
                    .coerceAtLeast(0)

                while (!isSafe && indexToRemove <= unsafeJumpIndex + 1) {
                    isSafe = line
                        .dropAt(indexToRemove)
                        .isSafe()

                    indexToRemove++
                }

                isSafe
            }

    private fun List<Int>.isSafe(): Boolean =
        indexOfUnsafeJump() == NOT_FOUND

    private fun List<Int>.indexOfUnsafeJump(): Int {
        var sign = 0

        for (i in 0 until lastIndex) {
            val diff = get(i) - get(i + 1)
            if (
                diff == 0 ||
                abs(diff) > 3 ||
                sign != 0 && diff.sign != sign
            ) {
                return i
            }
            sign = diff.sign
        }

        return NOT_FOUND
    }

    private fun convertData(input: List<String>): List<List<Int>> =
        input
            .map { line ->
                line
                    .split(" ")
                    .map { value ->
                        value
                            .trim()
                            .toInt()
                    }
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private const val NOT_FOUND = -1
private fun List<Int>.dropAt(index: Int): List<Int> =
    toMutableList()
        .apply { removeAt(index) }