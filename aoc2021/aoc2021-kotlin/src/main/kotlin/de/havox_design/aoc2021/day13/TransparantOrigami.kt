package de.havox_design.aoc2021.day13

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class TransparantOrigami(private var filename: String) {
    private val DELIMITER_COORDINATES = ","
    private val DELIMITER_ELEMENTS = "\n\n"
    private val DELIMITER_FOLDING = "="

    private val ICON_EMPTY = ""
    private val ICON_CARRIAGE_RETURN = "\r"

    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val (rawDots, rawFolds) = data
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITER_ELEMENTS)
        val points = rawDots
            .lines()
            .map { it.parseInts(DELIMITER_COORDINATES) }
            .map { Position2d(it.first(), it.last()) }
            .toSet()
        val folds = rawFolds
            .lines()
            .map { it.split(DELIMITER_FOLDING) }
            .map { it.first().last() to it.last().toInt() }
        val first = folds
            .first()

        return points
            .map {
                when {
                    first.first == 'x' && it.x > first.second -> Position2d(2 * first.second - it.x, it.y)
                    first.first == 'y' && it.y > first.second -> Position2d(it.x, 2 * first.second - it.y)
                    else -> it
                }
            }
            .toSet()
            .size
    }

    fun processPart2(): Any =
        0L

    private fun String?.parseInts(vararg delimiters: String, radix: Int = 10): List<Int> =
        this
            ?.split(*delimiters)
            ?.mapNotNull { it.trim().toIntOrNull(radix) }
            .orEmpty()

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
