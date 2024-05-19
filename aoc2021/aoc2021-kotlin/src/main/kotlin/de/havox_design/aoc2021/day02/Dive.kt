package de.havox_design.aoc2021.day02

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class Dive(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .fold(Position2d(0L,0L)) { (x, y), (direction, distance) ->
                when (direction) {
                    "forward" -> Position2d(x + distance.toInt(), y)
                    "down" -> Position2d(x, y + distance.toInt())
                    "up" -> Position2d(x, y - distance.toInt())
                    else -> throw IllegalArgumentException("Bad input $direction")
                }
            }
            .let { (x, y) -> x * y }

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<List<String>> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
            .map { line -> line.split(" ") }
}
