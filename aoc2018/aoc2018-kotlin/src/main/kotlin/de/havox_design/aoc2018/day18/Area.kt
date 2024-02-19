package de.havox_design.aoc2018.day18

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc.utils.kotlin.model.coordinates.adjacentCoordinates8Directions
import de.havox_design.aoc2018.day17.InputMap
import de.havox_design.aoc2018.day17.getTokens

typealias Area = InputMap<Acre>

fun Area.step() =
    getTokens()
    .associateBy { it.point }
    .mapValues { (_, acre) -> acre.next(this) }
    .forEach { (point, content) -> get(point)?.content = content }

fun Area.near(acre: Acre, min: Int, target: Content) =
    adjacentCoordinates8Directions(acre.point)
        .count { get(it)?.content == target } >= min

fun Area.get(point: Coordinate) =
    if (point.y in indices) {
        this[point.y]
            .tokens()
            .firstOrNull { it.atColumn(point.x) }
    } else {
        null
    }
