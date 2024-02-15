package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

abstract class InputToken(open var point: Coordinate) {

    abstract fun isActive(): Boolean

    fun x() =
        point.x

    fun y() =
        point.y

    fun atColumn(x: Int) =
        point.x == x

    fun atRow(y: Int) =
        point.y == y
}
