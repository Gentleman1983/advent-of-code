package de.havox_design.aoc2023.day03

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Point(val position: Position2d<Int>, val value: Char) {
    fun isNumber(): Boolean =
        value
            .isDigit()

    fun isSymbol(): Boolean =
        !isNumber() && value != '.'

    fun isGearSymbol(): Boolean =
        value == '*'

    fun getNeighboringCoordinates(): Set<Position2d<Int>> =
        setOf(
            Position2d(position.x, position.y - 1),
            Position2d(position.x + 1, position.y - 1),
            Position2d(position.x - 1, position.y - 1),
            Position2d(position.x + 1, position.y),
            Position2d(position.x - 1, position.y),
            Position2d(position.x, position.y + 1),
            Position2d(position.x + 1, position.y + 1),
            Position2d(position.x - 1, position.y + 1)
        )

    fun getLeftNeighborCoordinate(): Position2d<Int> =
        Position2d(position.x - 1, position.y)

    fun getRightNeighborCoordinate(): Position2d<Int> =
        Position2d(position.x + 1, position.y)
}
