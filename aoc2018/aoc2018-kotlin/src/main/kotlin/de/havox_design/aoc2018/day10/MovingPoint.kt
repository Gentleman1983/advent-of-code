package de.havox_design.aoc2018.day10

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class MovingPoint(var point: Position2d<Int>, val xVelocity: Int, val yVelocity: Int) {

    constructor(x: String, y: String, xVelocity: String, yVelocity: String) :
            this(Position2d(x.trim().toInt(), y.trim().toInt()), xVelocity.trim().toInt(), yVelocity.trim().toInt())

    fun forward(): Position2d<Int> {
        point = Position2d(point.x + xVelocity, point.y + yVelocity)
        return point
    }

    fun reverse(): Position2d<Int> {
        point = Position2d(point.x - xVelocity, point.y - yVelocity)
        return point
    }
}
