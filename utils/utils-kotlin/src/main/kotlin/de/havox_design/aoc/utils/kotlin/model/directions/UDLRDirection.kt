package de.havox_design.aoc.utils.kotlin.model.directions

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class UDLRDirection(val direction: Position2d<Int>) {
    DOWN(Position2d(0, 1)),
    LEFT(Position2d(-1, 0)),
    RIGHT(Position2d(1, 0)),
    UP(Position2d(0, -1))
}
