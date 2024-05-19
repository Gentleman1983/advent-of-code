package de.havox_design.aoc2021.day02

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.Position3d

class Dive(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .fold(Position2d(0L, 0L)) { (x, y), (direction, distance) ->
                when (direction) {
                    "forward" -> Position2d(x + distance.toLong(), y)
                    "down" -> Position2d(x, y + distance.toLong())
                    "up" -> Position2d(x, y - distance.toLong())
                    else -> throw IllegalArgumentException("Bad input $direction")
                }
            }
            .let { (x, y) -> x * y }

    fun processPart2(): Any =
        data
            .fold(Position3d(0L, 0L, 0L)) { (x, y, aim), (direction, distance) ->
                when (direction) {
                    "forward" -> Position3d(x + distance.toLong(), y + aim * distance.toLong(), aim)
                    "down" -> Position3d(x, y, aim + distance.toLong())
                    "up" -> Position3d(x, y, aim - distance.toLong())
                    else -> throw IllegalArgumentException("Bad input $direction")
                }
            }
            .let { (x, y, _) -> x * y }

    private fun getResourceAsText(path: String): List<List<String>> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
            .map { line -> line.split(" ") }
}
