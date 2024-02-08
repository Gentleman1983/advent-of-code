package de.havox_design.aoc2018.day11

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Cell(val position: Position2d<Int>, val value: Int) {

    constructor(x: Int, y: Int, serial: Int) :
            this(
                Position2d(x, y),
                (x + 10).let {
                    ((((it * y) + serial) * it / 100) % 10) - 5
                }
            )

    fun cumulativeSum(grid: Map<Position2d<Int>, Int>) =
        value + setOf(position.n(), position.w()).sumOf {
            grid[it] ?: 0
        } - (grid[position.nw()] ?: 0)
}

fun Position2d<Int>.n(offset: Int = 1) = Position2d(x, y - offset)
fun Position2d<Int>.nw(offset: Int = 1) = Position2d(x - offset, y - offset)
fun Position2d<Int>.w(offset: Int = 1) = Position2d(x - offset, y)
