package de.havox_design.aoc2024.day04

enum class Direction(val dx: Int, val dy: Int) {
    EAST(1, 0),
    NORTHEAST(1, -1),
    NORTH(0, -1),
    NORTHWEST(-1, -1),
    WEST(-1, 0),
    SOUTHWEST(-1, 1),
    SOUTH(0, 1),
    SOUTHEAST(1, 1),
    ;

    operator fun component1() = dx
    operator fun component2() = dy
}
