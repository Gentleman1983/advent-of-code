package de.havox_design.aoc2021.day03

import de.havox_design.aoc.utils.kotlin.helpers.sumWith
import kotlin.math.pow

class BinaryDiagnostic(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val gamma = data
            .map { line -> line.map(Char::digitToInt) }
            .reduce(List<Int>::sumWith)
            .map { if (it >= data.size - it) 1 else 0 }
            .digitsToInt(2)

        return gamma * (gamma.inv() and bitmask(data.first().length))
    }

    fun processPart2(): Any {
        val lineDigits = data
            .map { line ->
                line
                    .map(Char::digitToInt)
            }

        return calculateRating(lineDigits, 1) * calculateRating(lineDigits, 0)
    }

    private fun Iterable<Int>.digitsToInt(radix: Int) =
        reduce { acc, digit -> acc * radix + digit }

    private fun bitmask(size: Int) =
        2.pow(size) - 1

    private fun Int.pow(n: Int) =
        this
            .toDouble()
            .pow(n)
            .toInt()

    private tailrec fun calculateRating(lists: List<List<Int>>, highBit: Int, index: Int = 0): Int {
        return if (lists.size == 1) {
            lists
                .first()
                .digitsToInt(2)
        }
        else {
            val oneCounts = lists
                .sumOf { it[index] }
            val comparisonBit = if (oneCounts >= lists.size - oneCounts) {
                highBit
            }
            else {
                1 - highBit
            }

            calculateRating(lists.filter { it[index] == comparisonBit }, highBit, index + 1)
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
