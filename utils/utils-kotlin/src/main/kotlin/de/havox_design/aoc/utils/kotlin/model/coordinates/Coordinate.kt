package de.havox_design.aoc.utils.kotlin.model.coordinates

import kotlin.math.abs

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {
    override fun compareTo(other: Coordinate): Int =
        when (val result = y.compareTo(other.y)) {
            0 -> x.compareTo(other.x)
            else -> result
        }

    operator fun minus(other: Coordinate): Coordinate =
        Coordinate(x - other.x, y - other.y)

    operator fun plus(other: Coordinate): Coordinate =
        Coordinate(x + other.x, y + other.y)

    operator fun plus(i: Number): Coordinate =
        plus(Coordinate(i.toInt(), i.toInt()))

    operator fun times(i: Number): Coordinate =
        this * Coordinate(i.toInt(), i.toInt())

    operator fun times(other: Coordinate): Coordinate =
        Coordinate(x * other.x, y * other.y)

    operator fun div(other: Coordinate): Coordinate =
        Coordinate(x / other.x, y / other.y)
}

fun manhattanDistance(a: Coordinate, b: Coordinate) =
    abs(a.x - b.x) + abs(a.y - b.y)

val origin = Coordinate(0, 0)

fun <V> Map<Coordinate, V>.yRange() =
    keys.minByOrNull { it.y }!!.y to keys.maxByOrNull { it.y }!!.y

fun <V> Map<Coordinate, V>.xRange() =
    keys.minByOrNull { it.x }!!.x to keys.maxByOrNull { it.x }!!.x

fun adjacentCoordinates(origin: Coordinate) = sequenceOf(
    Coordinate(origin.x + 1, origin.y),
    Coordinate(origin.x - 1, origin.y),
    Coordinate(origin.x, origin.y + 1),
    Coordinate(origin.x, origin.y - 1)
)

