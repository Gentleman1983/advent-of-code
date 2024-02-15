package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

data class FlowingWater(override var point: Coordinate) : Water(point) {
    override fun isActive() = true
    override fun toString() = "|"
}
