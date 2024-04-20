package de.havox_design.aoc2020.day11

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc2020.day11.SeatingSystem.Companion.ICON_EMPTY_SEAT
import de.havox_design.aoc2020.day11.SeatingSystem.Companion.ICON_FLOOR
import de.havox_design.aoc2020.day11.SeatingSystem.Companion.ICON_OCCUPIED_SEAT

class Seats(private val seats: Array<CharArray>) {
    fun count(c: Char): Int =
        seats
            .sumOf { it.count { value -> value == c } }

    private val width: Int
        get() = seats[0].size
    private val height = seats.size
    private val yRange = seats.indices
    private val xRange: IntRange
        get() = seats[0].indices
    private val indices: List<Position2d<Int>>
        get() = xRange.flatMap { x -> yRange.map { y -> Position2d(x, y) } }

    override operator fun equals(other: Any?): Boolean =
        other is Seats && seats.contentDeepEquals(other.seats)

    override fun hashCode(): Int =
        seats
            .contentDeepHashCode()

    operator fun contains(point: Position2d<Int>) =
        point.x in xRange && point.y in yRange

    operator fun get(it: Position2d<Int>): Char =
        seats[it.y][it.x]

    operator fun set(it: Position2d<Int>, char: Char) {
        seats[it.y][it.x] = char
    }

    fun simulateUntilStable(calculateSeat: (Seats, Position2d<Int>) -> Char): Seats {
        var new = this
        var previousSeats = sized(0, 0)

        while (new != previousSeats) {
            previousSeats = new
            new = run {
                val newSeats = sized(previousSeats.width, previousSeats.height)

                for (point in newSeats.indices) {
                    newSeats[point] = calculateSeat(previousSeats, point)
                }

                newSeats
            }
        }

        return new
    }

    fun calculateOccupiedSeatsPart1(point: Position2d<Int>): Char {
        val current = this[point]
        val adjacent = directions
            .map { point + it }
            .filter { it in this }
            .map { this[it] }

        return when {
            current == ICON_EMPTY_SEAT && adjacent.count { it == ICON_OCCUPIED_SEAT } == 0 -> ICON_OCCUPIED_SEAT
            current == ICON_OCCUPIED_SEAT && adjacent.count { it == ICON_OCCUPIED_SEAT } >= 4 -> ICON_EMPTY_SEAT
            else -> this[point]
        }
    }

    fun calculateOccupiedSeatsPart2(point: Position2d<Int>): Char {
        val currentValue = this[point]

        val seen = directions
            .mapNotNull {
                pointsInDirection(point, it)
                    .firstOrNull { p -> this[p] != ICON_FLOOR }
            }
            .map { this[it] }

        return when {
            currentValue == ICON_EMPTY_SEAT && seen.count { it == ICON_OCCUPIED_SEAT } == 0 -> ICON_OCCUPIED_SEAT
            currentValue == ICON_OCCUPIED_SEAT && seen.count { it == ICON_OCCUPIED_SEAT } >= 5 -> ICON_EMPTY_SEAT
            else -> this[point]
        }
    }

    private fun pointsInDirection(start: Position2d<Int>, direction: Position2d<Int>): Sequence<Position2d<Int>> =
        generateSequence(start + direction) { it + direction }
            .takeWhile { it in this }

    companion object {
        private val directions = listOf(
            Position2d(1, 0),
            Position2d(-1, 0),
            Position2d(0, +1),
            Position2d(0, -1),
            Position2d(+1, -1),
            Position2d(-1, -1),
            Position2d(+1, +1),
            Position2d(-1, +1)
        )

        private fun sized(width: Int, height: Int): Seats =
            Seats(Array(height) { CharArray(width) })
    }

    private operator fun Position2d<Int>.plus(other: Position2d<Int>) =
        Position2d(x + other.x, y + other.y)
}
