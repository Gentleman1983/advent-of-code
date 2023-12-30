package de.havox_design.aoc2023.day17

enum class FourDirectionFlipped(private val direction: Coordinate) {
    DOWN(Coordinate(0, 1)),
    UP(Coordinate(0, -1)),
    LEFT(Coordinate(-1, 0)),
    RIGHT(Coordinate(1, 0));

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
}
