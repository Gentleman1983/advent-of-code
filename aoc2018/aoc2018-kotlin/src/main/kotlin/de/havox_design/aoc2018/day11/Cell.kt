package de.havox_design.aoc2018.day11

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.north
import de.havox_design.aoc.utils.kotlin.model.positions.northwest
import de.havox_design.aoc.utils.kotlin.model.positions.west

data class Cell(val position: Position2d<Int>, val value: Int) {

    constructor(x: Int, y: Int, serial: Int) :
            this(
                Position2d(x, y),
                (x + 10).let {
                    ((((it * y) + serial) * it / 100) % 10) - 5
                }
            )

    fun cumulativeSum(grid: Map<Position2d<Int>, Int>) =
        value + setOf(position.north(), position.west()).sumOf {
            grid[it] ?: 0
        } - (grid[position.northwest()] ?: 0)
}
