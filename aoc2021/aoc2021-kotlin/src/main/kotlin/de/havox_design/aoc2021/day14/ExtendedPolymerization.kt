package de.havox_design.aoc2021.day14

import de.havox_design.aoc.utils.kotlin.helpers.toPair
import java.math.BigInteger

class ExtendedPolymerization(private var filename: String) {
    private val DELIMITER_ELEMENTS = "\n\n"
    private val DELIMITER_TEMPLATE = " -> "
    private val ICON_CARRIAGE_RETURN = "\r"
    private val ICON_EMPTY = ""

    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        solve(40)

    private fun solve(steps: Int = 10): BigInteger {
        val (rawStart, rawPatterns) = data
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITER_ELEMENTS)
        val patterns = rawPatterns
            .lines()
            .map { it.split(DELIMITER_TEMPLATE).toPair() }
            .associate { it.first to setOf("${it.first.first()}${it.second}", "${it.second}${it.first.last()}") }
        val start = rawStart
            .windowed(2)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toBigInteger() }
        val end = (1..steps)
            .fold(start) { acc, _ ->
                val next = mutableMapOf<String, BigInteger>()

                acc
                    .forEach { (chunk, size) ->
                        (patterns[chunk] ?: setOf(chunk))
                            .forEach { newChunk ->
                                next[newChunk] = next.getOrDefault(newChunk, BigInteger.ZERO) + size
                            }
                    }

                next.toMap()
            }
        val chars = end
            .toList()
            .flatMap { listOf(it.first.first() to it.second, it.first.last() to it.second) }
        val doubleChars = chars + listOf(rawStart.first() to BigInteger.ONE, rawStart.last() to BigInteger.ONE)
        val charMap = doubleChars
            .groupBy { it.first }
            .mapValues { entry -> entry.value.sumOf { it.second } }

        return (charMap.maxOf { it.value } - charMap.minOf { it.value }).divide(BigInteger.TWO)
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
