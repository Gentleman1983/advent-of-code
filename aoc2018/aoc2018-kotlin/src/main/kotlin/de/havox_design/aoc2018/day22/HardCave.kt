package de.havox_design.aoc2018.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class HardCave(val depth: Int, val target: Position2d<Int>) {

    private val state = mutableMapOf<Position2d<Int>, Region>()

    fun at(point: Position2d<Int>) = state.getOrPut(point) { Region(point, this) }

    fun getTotalRiskRiskLevel() = state.values.sumOf { it.regionType.riskLevel }
}
