package de.havox_design.aoc2018.day23

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d

data class NanobotPartTwo(val point: Position3d<Int>, val radius: Int) {

    fun inTotalRangeTo(other: NanobotPartTwo) =
        point.manhattanDistance(other.point) <= radius + other.radius

    companion object {
        @JvmStatic
        fun from(x: String, y: String, z: String, r: String): NanobotPartTwo =
            NanobotPartTwo(Position3d(x.toInt(), y.toInt(), z.toInt()), r.toInt())
    }
}