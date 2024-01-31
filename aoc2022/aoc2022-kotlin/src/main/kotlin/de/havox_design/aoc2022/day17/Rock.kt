package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class Rock(val dimensionX: Long, val dimensionY: Long, private val structure: Map<Position2d<Long>, String>) {
    HORIZONTAL_LINE(
        4L,
        1L,
        mapOf(
            Pair(Position2d(0L,0L), "#"),
            Pair(Position2d(1L,0L), "#"),
            Pair(Position2d(2L,0L), "#"),
            Pair(Position2d(3L,0L), "#")
        )
    ),
    PLUS(
        3L,
        3L,
        mapOf(
            Pair(Position2d(0L,2L), "."),
            Pair(Position2d(1L,2L), "#"),
            Pair(Position2d(2L,2L), "."),
            Pair(Position2d(0L,1L), "#"),
            Pair(Position2d(1L,1L), "#"),
            Pair(Position2d(2L,1L), "#"),
            Pair(Position2d(0L,0L), "."),
            Pair(Position2d(1L,0L), "#"),
            Pair(Position2d(2L,0L), ".")
        )
    ),
    ARROW(
        3L,
        3L,
        mapOf(
            Pair(Position2d(0L,2L), "."),
            Pair(Position2d(1L,2L), "."),
            Pair(Position2d(2L,2L), "#"),
            Pair(Position2d(0L,1L), "."),
            Pair(Position2d(1L,1L), "."),
            Pair(Position2d(2L,1L), "#"),
            Pair(Position2d(0L,0L), "#"),
            Pair(Position2d(1L,0L), "#"),
            Pair(Position2d(2L,0L), "#")
        )
    ),
    VERTICAL_LINE(
        1L,
        4L,
        mapOf(
            Pair(Position2d(0L,3L), "#"),
            Pair(Position2d(0L,2L), "#"),
            Pair(Position2d(0L,1L), "#"),
            Pair(Position2d(0L,0L), "#")
        )
    ),
    BOX(
        2L,
        2L,
        mapOf(
            Pair(Position2d(0L,1L), "#"),
            Pair(Position2d(1L,1L), "#"),
            Pair(Position2d(0L,0L), "#"),
            Pair(Position2d(1L,0L), "#")
        )
    );

    fun getBlockedPositions(): Set<Position2d<Long>> =
        structure
            .entries
            .filter { entry -> entry.value == "#" }
            .map { entry -> entry.key }
            .toSet()
}