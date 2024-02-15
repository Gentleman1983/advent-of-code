package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import kotlin.properties.Delegates

typealias Boundary = Pair<Coordinate, Coordinate>

fun getBoundary(points: Collection<Coordinate>): Boundary {
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

    return Coordinate(xMin, yMin) to Coordinate(xMax, yMax)
}

fun Boundary.getWidth() =
    second.x - first.x + 1

fun Boundary.getHeight() =
    second.y - first.y + 1

fun Boundary.contains(point: Coordinate) =
    point.x in (first.x..second.x) &&
            point.y in (first.y..second.y)
