package de.havox_design.aoc.utils.kotlin.model.chargrid

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class PointVal(val point: Position2d<Int>, val value: Char) {
}

operator fun Position2d<Int>.plus(other: Position2d<Int>): Position2d<Int> =
    Position2d(this.x + other.x, this.y + other.y)