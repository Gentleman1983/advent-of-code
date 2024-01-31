package de.havox_design.aoc2016.day24

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Position(val position: Position2d<Int>, val value: String) {
    val isValid = position.x >= 0
            && position.y >= 0
    val isWall = value == "#"
    val n = try {
        value.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
