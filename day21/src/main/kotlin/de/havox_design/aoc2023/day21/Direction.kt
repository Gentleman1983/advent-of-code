package de.havox_design.aoc2023.day21

enum class Direction(val direction: Pair<Int, Int>) {
    DOWN(Pair(0, 1)),
    LEFT(Pair(-1, 0)),
    RIGHT(Pair(1, 0)),
    UP(Pair(0, -1))
}