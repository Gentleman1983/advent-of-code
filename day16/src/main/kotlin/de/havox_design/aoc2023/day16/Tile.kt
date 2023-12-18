package de.havox_design.aoc2023.day16

import de.havox_design.aoc2023.day10.Direction

data class Tile(val row: Int, val column: Int, val type: Char) {
    private val MARKER_UP = "^"
    private val MARKER_DOWN = "V"
    private val MARKER_LEFT = "<"
    private val MARKER_RIGHT = ">"
    private val MARKER_EMPTY = '.'
    private val MARKER_NORTH_EAST_MIRROR = '\\'
    private val MARKER_SOUTH_EAST_MIRROR = '/'
    private val MARKER_ROW_SPLITTER = '|'
    private val MARKER_COL_SPLITTER = '-'

    private val enteredDirections = mutableSetOf<Direction>()

    fun isEnergized(): Boolean = enteredDirections.isNotEmpty()

    fun getNextDirections(enteredDirection: Direction): List<Direction> {
        if (enteredDirection == Direction.NONE || !enteredDirections.add(enteredDirection)) {
            return listOf(Direction.NONE)
        }
        return when (type) {
            MARKER_EMPTY -> listOf(enteredDirection)
            MARKER_SOUTH_EAST_MIRROR -> when (enteredDirection) {
                Direction.NORTH -> listOf(Direction.EAST)
                Direction.EAST -> listOf(Direction.NORTH)
                Direction.SOUTH -> listOf(Direction.WEST)
                Direction.WEST -> listOf(Direction.SOUTH)
                else -> listOf(Direction.NONE)
            }

            MARKER_NORTH_EAST_MIRROR -> when (enteredDirection) {
                Direction.NORTH -> listOf(Direction.WEST)
                Direction.EAST -> listOf(Direction.SOUTH)
                Direction.SOUTH -> listOf(Direction.EAST)
                Direction.WEST -> listOf(Direction.NORTH)
                else -> listOf(Direction.NONE)
            }

            MARKER_COL_SPLITTER -> {
                if (enteredDirection in listOf(Direction.WEST, Direction.EAST)) listOf(enteredDirection)
                else listOf(Direction.WEST, Direction.EAST)
            }

            MARKER_ROW_SPLITTER -> {
                if (enteredDirection in listOf(Direction.NORTH, Direction.SOUTH)) listOf(enteredDirection)
                else listOf(Direction.NORTH, Direction.SOUTH)
            }

            else -> listOf(Direction.NONE)
        }
    }

    @SuppressWarnings("kotlin:S6510")
    override fun toString(): String {
        when {
            enteredDirections.size > 1 -> {
                return enteredDirections.size.toString()
            }

            else -> return when (this.type) {
                MARKER_EMPTY -> when (enteredDirections.firstOrNull()) {
                    Direction.NORTH -> MARKER_UP
                    Direction.EAST -> MARKER_RIGHT
                    Direction.SOUTH -> MARKER_DOWN
                    Direction.WEST -> MARKER_LEFT
                    else -> this.type.toString()
                }

                else -> this.type.toString()
            }
        }
    }
}
