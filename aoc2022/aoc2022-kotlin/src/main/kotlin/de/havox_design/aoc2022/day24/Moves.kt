package de.havox_design.aoc2022.day24

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class Moves(private val function: (Position2d<Int>) -> Position2d<Int>) {
    WAIT({ pos -> Position2d(pos.x, pos.y) }),
    NORTH({ pos -> Position2d(pos.x, pos.y - 1) }),
    EAST({ pos -> Position2d(pos.x + 1, pos.y) }),
    SOUTH({ pos -> Position2d(pos.x, pos.y + 1) }),
    WEST({ pos -> Position2d(pos.x - 1, pos.y) });

    fun getDirection(pos: Position2d<Int>): Position2d<Int> =
        function(pos)
}

private fun Pair<Int, Int>.getX(): Int = this.first
private fun Pair<Int, Int>.getY(): Int = this.second
