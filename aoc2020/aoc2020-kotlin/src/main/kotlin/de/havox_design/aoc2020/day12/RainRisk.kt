package de.havox_design.aoc2020.day12

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection
import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection.*
import de.havox_design.aoc.utils.kotlin.model.positions.*

class RainRisk(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .fold(Pair(EAST, Position2d(0, 0))) { (direction, position), line ->
                val action = line[0]
                val value = line.substring(1, line.length).toInt()

                when (action) {
                    ACTION_NORTH -> Pair(direction, position + getPostition(NORTH) * value)
                    ACTION_SOUTH -> Pair(direction, position + getPostition(SOUTH) * value)
                    ACTION_EAST -> Pair(direction, position + getPostition(EAST) * value)
                    ACTION_WEST -> Pair(direction, position + getPostition(WEST) * value)
                    ACTION_LEFT -> Pair(direction.left(value / 90), position)
                    ACTION_RIGHT -> Pair(direction.right(value / 90), position)
                    ACTION_FORWARD -> Pair(direction, position + getPostition(direction) * value)
                    else -> throw UnsupportedOperationException()
                }
            }
            .second
            .abs()

    fun processPart2(): Any =
        data
            .fold(Pair(Position2d(0, 0), Position2d(10, 1))) { (ship, waypoint), line ->
                val action = line[0]
                val value = line
                    .substring(1, line.length)
                    .toInt()

                when (action) {
                    ACTION_NORTH -> Pair(ship, waypoint + getPostition(NORTH) * value)
                    ACTION_SOUTH -> Pair(ship, waypoint + getPostition(SOUTH) * value)
                    ACTION_EAST -> Pair(ship, waypoint + getPostition(EAST) * value)
                    ACTION_WEST -> Pair(ship, waypoint + getPostition(WEST) * value)
                    ACTION_LEFT -> Pair(ship, waypoint.counterClockwise(value))
                    ACTION_RIGHT -> Pair(ship, waypoint.clockwise(value))
                    ACTION_FORWARD -> Pair(ship + waypoint * value, waypoint)
                    else -> throw UnsupportedOperationException()
                }
            }
            .first
            .abs()

    private fun getPostition(direction: GeoDirection) =
        when (direction) {
            NORTH -> Position2d(0, 1)
            EAST -> Position2d(1, 0)
            SOUTH -> Position2d(0, -1)
            WEST -> Position2d(-1, 0)
            NONE -> Position2d(0, 0)
        }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ACTION_EAST = 'E'
        private const val ACTION_FORWARD = 'F'
        private const val ACTION_LEFT = 'L'
        private const val ACTION_NORTH = 'N'
        private const val ACTION_RIGHT = 'R'
        private const val ACTION_SOUTH = 'S'
        private const val ACTION_WEST = 'W'

    }
}
