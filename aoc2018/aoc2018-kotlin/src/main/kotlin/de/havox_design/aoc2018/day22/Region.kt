package de.havox_design.aoc2018.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.north
import de.havox_design.aoc.utils.kotlin.model.positions.west

data class Region(val point: Position2d<Int>, val cave: HardCave) {

    val geologicIndex: Int by lazy { deriveGeologicIndex() }
    val erosionLevel: Int by lazy { (geologicIndex + cave.depth) % 20183 }
    val regionType: RegionType by lazy { RegionType.entries[erosionLevel % 3] }

    private val atEnds = point in setOf(Position2d(0, 0), cave.target)

    private fun deriveGeologicIndex() =
        when {
            atEnds -> 0
            point.y == 0 -> point.x * 16807
            point.x == 0 -> point.y * 48271
            else -> (cave.at(point.west()) to cave.at(point.north()))
                .let { it.first.erosionLevel * it.second.erosionLevel }
        }

    fun toolsRequired(): Set<Tool> = if (atEnds) setOf(Tool.TORCH) else
        when (regionType) {
            RegionType.ROCKY -> setOf(Tool.CLIMBING_GEAR, Tool.TORCH)
            RegionType.WET -> setOf(Tool.CLIMBING_GEAR, Tool.NEITHER)
            RegionType.NARROW -> setOf(Tool.TORCH, Tool.NEITHER)
        }
}
