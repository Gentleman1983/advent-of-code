package de.havox_design.aoc.utils.kotlin.model.coordinates

enum class FourDirections(private val direction: Coordinate) {
    DOWN(Coordinate(0, -1)),
    LEFT(Coordinate(-1, 0)),
    RIGHT(Coordinate(1, 0)),
    UP(Coordinate(0, 1));

    operator fun plus(other: Coordinate) =
        direction + other

    fun turnLeft() = when (this) {
        UP -> LEFT
        LEFT -> DOWN
        DOWN -> RIGHT
        RIGHT -> UP
    }

    fun turnRight() = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }

    fun turnAround() = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }

    fun dx(): Int =
        direction.x

    fun dy(): Int =
        direction.y
}
