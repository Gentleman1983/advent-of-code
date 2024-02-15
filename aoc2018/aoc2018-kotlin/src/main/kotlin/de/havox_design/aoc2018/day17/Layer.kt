package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

typealias Layer = MutableInputLine<Clay, Water>

fun Layer.nothingAt(point: Coordinate) =
    tokens().none { it.point == point } && additions.none { it.point == point }
