package de.havox_design.aoc2024.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class RobotDirection(val delta: Position2d<Int>) {
    EAST(Position2d(1, 0)),
    NORTH(Position2d(0, -1)),
    WEST(Position2d(-1, 0)),
    SOUTH(Position2d(0, 1));

    fun turnLeft(): RobotDirection {
        return entries[(this.ordinal + 1) % entries.size]
    }

    fun turnRight(): RobotDirection {
        return entries[(entries.size + (this.ordinal - 1)) % entries.size]
    }

    fun isHorizontal(): Boolean =
        this == WEST || this == EAST

    fun isVertical(): Boolean =
        this == SOUTH || this == NORTH

    companion object {
        fun ofArrow(char: Char): RobotDirection? =
            when (char) {
                '^' -> NORTH
                '>' -> EAST
                '<' -> WEST
                'v' -> SOUTH
                else -> null
            }
    }
}
