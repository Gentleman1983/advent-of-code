package de.havox_design.aoc2016.day24

data class Position(val x: Int, val y: Int, val value: String) {
    val isValid = x >= 0
            && y >= 0
    val isWall = value == "#"
    val n = try {
        value.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
