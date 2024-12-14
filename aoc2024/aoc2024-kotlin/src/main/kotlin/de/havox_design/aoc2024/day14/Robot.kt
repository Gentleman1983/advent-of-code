package de.havox_design.aoc2024.day14

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc2024.day14.RestroomRedoubt.Companion.BATHROOM_HEIGHT
import de.havox_design.aoc2024.day14.RestroomRedoubt.Companion.BATHROOM_WIDTH

data class Robot(val position: Position2d<Int>, val velocity: Position2d<Int>) {
    fun move(): Robot {
        val x = (position.x + velocity.x)
            .mod(BATHROOM_WIDTH)
        val y = (position.y + velocity.y)
            .mod(BATHROOM_HEIGHT)

        return Robot(Position2d(x, y), velocity)
    }

    companion object {
        private val ROBOT_REGEX = """p=(\d+),(\d+) v=(-?\d+),(-?\d+)"""
            .toRegex()

        operator fun invoke(input: String): Robot {
            val (px, py, vx, vy) = ROBOT_REGEX
                .find(input)!!
                .destructured

            return Robot(Position2d(px.toInt(), py.toInt()), Position2d(vx.toInt(), vy.toInt()))
        }
    }
}
