package de.havox_design.aoc2021.day03

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

    fun processPart2(): Any =
        0L

    private fun Iterable<Int>.digitsToInt(radix: Int) =
        reduce { acc, digit -> acc * radix + digit }

    private fun bitmask(size: Int) =
        2.pow(size) - 1

    private fun Int.pow(n: Int) =
        this
            .toDouble()
            .pow(n)
            .toInt()

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private fun List<Int>.sumWith(other: List<Int>) =
    zip(other, Int::plus)
