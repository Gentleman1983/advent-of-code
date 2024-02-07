package de.havox_design.aoc2018.day10

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.properties.Delegates

typealias Boundary = Pair<Position2d<Int>, Position2d<Int>>

fun getBoundary(points: Collection<Position2d<Int>>): Boundary {
    var xMin: Int by Delegates.vetoable(Int.MAX_VALUE) { _, old, new -> new < old }
    var xMax: Int by Delegates.vetoable(Int.MIN_VALUE) { _, old, new -> new > old }
    var yMin: Int by Delegates.vetoable(Int.MAX_VALUE) { _, old, new -> new < old }
    var yMax: Int by Delegates.vetoable(Int.MIN_VALUE) { _, old, new -> new > old }
    points.forEach {
        xMin = it.x
        xMax = it.x
        yMin = it.y
        yMax = it.y
    }
    return Position2d(xMin, yMin) to Position2d(xMax, yMax)
}

fun Boundary.getWidth() = second.x - first.x + 1

fun Boundary.getHeight() = second.y - first.y + 1

fun Boundary.getArea() = getWidth().toLong() * getHeight().toLong()

fun Boundary.getOffset(target: Position2d<Int>) = target.x - first.x to target.y - first.y

fun Boundary.contains(point: Position2d<Int>) =
    point.x in (first.x..second.x) && point.y in (first.y..second.y)