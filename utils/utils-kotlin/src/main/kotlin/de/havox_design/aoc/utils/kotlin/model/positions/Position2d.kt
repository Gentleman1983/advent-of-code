package de.havox_design.aoc.utils.kotlin.model.positions

data class Position2d<t>(val x: t, val y: t)

fun Position2d<Int>.east(offset: Int = 1) =
    Position2d(x + offset, y)

fun Position2d<Int>.north(offset: Int = 1) =
    Position2d(x, y - offset)

fun Position2d<Int>.northeast(offset: Int = 1) =
    Position2d(x + offset, y - offset)

fun Position2d<Int>.northwest(offset: Int = 1) =
    Position2d(x - offset, y - offset)

fun Position2d<Int>.south(offset: Int = 1) =
    Position2d(x, y + offset)

fun Position2d<Int>.southeast(offset: Int = 1) =
    Position2d(x + offset, y + offset)

fun Position2d<Int>.southwest(offset: Int = 1) =
    Position2d(x - offset, y + offset)

fun Position2d<Int>.west(offset: Int = 1) =
    Position2d(x - offset, y)

operator fun Position2d<Int>.plus(other: Position2d<Int>) =
    Position2d(x + other.x, y + other.y)

operator fun Position2d<Long>.plus(other: Position2d<Long>): Position2d<Long> =
    Position2d(x + other.x, y + other.y)

operator fun Position2d<Int>.minus(other: Position2d<Int>): Position2d<Int> =
    Position2d(x - other.x, y - other.y)

operator fun Position2d<Int>.times(value: Int) =
    Position2d(x * value, y * value)

fun Position2d<Int>.abs() =
    kotlin.math.abs(x) + kotlin.math.abs(y)

fun Position2d<Int>.clockwise(degrees: Int): Position2d<Int> {
    return when (degrees) {
        90 -> Position2d(y, -x)
        180 -> Position2d(-x, -y)
        270 -> Position2d(-y, x)
        else -> throw UnsupportedOperationException()
    }
}

fun Position2d<Int>.counterClockwise(degrees: Int): Position2d<Int> =
    clockwise(360 - degrees)

fun Position2d<Int>.neighbours() =
    listOf(
        Position2d(x + 1, y),
        Position2d(x - 1, y),
        Position2d(x, y + 1),
        Position2d(x, y - 1)
    )

fun Position2d<Int>.diagonalNeighbours() =
    listOf(
        Position2d(x + 1, y + 1),
        Position2d(x + 1, y - 1),
        Position2d(x - 1, y + 1),
        Position2d(x - 1, y - 1)
    )
