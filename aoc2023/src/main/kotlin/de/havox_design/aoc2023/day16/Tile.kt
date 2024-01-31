package de.havox_design.aoc2023.day16

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Tile(val position: Position2d<Int>, val type: Char) {
    private val MARKER_UP = "^"
    private val MARKER_DOWN = "V"
    private val MARKER_LEFT = "<"
    private val MARKER_RIGHT = ">"
    private val MARKER_EMPTY = '.'
    private val MARKER_NORTH_EAST_MIRROR = '\\'
    private val MARKER_SOUTH_EAST_MIRROR = '/'
    private val MARKER_ROW_SPLITTER = '|'
    private val MARKER_COL_SPLITTER = '-'

    private val enteredDirections = mutableSetOf<GeoDirection>()

    fun isEnergized(): Boolean = enteredDirections.isNotEmpty()

    fun getNextDirections(enteredDirection: GeoDirection): List<GeoDirection> {
        if (enteredDirection == GeoDirection.NONE || !enteredDirections.add(enteredDirection)) {
            return listOf(GeoDirection.NONE)
        }
        return when (type) {
            MARKER_EMPTY -> listOf(enteredDirection)
            MARKER_SOUTH_EAST_MIRROR -> when (enteredDirection) {
                GeoDirection.NORTH -> listOf(GeoDirection.EAST)
                GeoDirection.EAST -> listOf(GeoDirection.NORTH)
                GeoDirection.SOUTH -> listOf(GeoDirection.WEST)
                GeoDirection.WEST -> listOf(GeoDirection.SOUTH)
                else -> listOf(GeoDirection.NONE)
            }

            MARKER_NORTH_EAST_MIRROR -> when (enteredDirection) {
                GeoDirection.NORTH -> listOf(GeoDirection.WEST)
                GeoDirection.EAST -> listOf(GeoDirection.SOUTH)
                GeoDirection.SOUTH -> listOf(GeoDirection.EAST)
                GeoDirection.WEST -> listOf(GeoDirection.NORTH)
                else -> listOf(GeoDirection.NONE)
            }

            MARKER_COL_SPLITTER -> {
                if (enteredDirection in listOf(GeoDirection.WEST, GeoDirection.EAST)) listOf(enteredDirection)
                else listOf(GeoDirection.WEST, GeoDirection.EAST)
            }

            MARKER_ROW_SPLITTER -> {
                if (enteredDirection in listOf(GeoDirection.NORTH, GeoDirection.SOUTH)) listOf(enteredDirection)
                else listOf(GeoDirection.NORTH, GeoDirection.SOUTH)
            }

            else -> listOf(GeoDirection.NONE)
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
                    GeoDirection.NORTH -> MARKER_UP
                    GeoDirection.EAST -> MARKER_RIGHT
                    GeoDirection.SOUTH -> MARKER_DOWN
                    GeoDirection.WEST -> MARKER_LEFT
                    else -> this.type.toString()
                }

                else -> this.type.toString()
            }
        }
    }
}
