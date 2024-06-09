package de.havox_design.aoc2021.day13

import de.havox_design.aoc.utils.kotlin.helpers.parseInts
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class TransparantOrigami(private var filename: String) {
    private val DELIMITER_COORDINATES = ","
    private val DELIMITER_ELEMENTS = "\n\n"
    private val DELIMITER_FOLDING = "="

    private val ICON_BLACK = " "
    private val ICON_CARRIAGE_RETURN = "\r"
    private val ICON_EMPTY = ""
    private val ICON_NEWLINE = "\n"
    private val ICON_WHITE = "#"

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

    fun processPart2(): Any {
        val (rawDots, rawFolds) = data
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITER_ELEMENTS)
        val points = rawDots
            .lines()
            .map { it.parseInts(DELIMITER_COORDINATES) }
            .map { Position2d(it.first(), it.last()) }
            .toSet()
        val folds = rawFolds.lines()
            .map { it.split(DELIMITER_FOLDING) }
            .map { it.first().last() to it.last().toInt() }
        val final = folds
            .fold(points) { acc, fold ->
                acc
                    .map {
                        when {
                            fold.first == 'x' && it.x > fold.second -> Position2d(2 * fold.second - it.x, it.y)
                            fold.first == 'y' && it.y > fold.second -> Position2d(it.x, 2 * fold.second - it.y)
                            else -> it
                        }
                    }
                    .toSet()
            }
        val minX = final.minOf { it.x }
        val maxX = final.maxOf { it.x }
        val minY = final.minOf { it.y }
        val maxY = final.maxOf { it.y }

        return (minY..maxY)
            .joinToString(prefix = ICON_NEWLINE, separator = ICON_NEWLINE) { y ->
                (minX..maxX)
                    .joinToString(separator = ICON_EMPTY) { x ->
                        if (Position2d(x, y) in final) {
                            ICON_WHITE
                        } else {
                            ICON_BLACK
                        }
                    }
            }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
