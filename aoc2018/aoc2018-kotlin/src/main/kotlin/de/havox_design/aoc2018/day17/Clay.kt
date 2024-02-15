package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

data class Clay(override var point: Coordinate) : InputToken(point) {
    override fun isActive() = true
    override fun toString() = "#"
}
