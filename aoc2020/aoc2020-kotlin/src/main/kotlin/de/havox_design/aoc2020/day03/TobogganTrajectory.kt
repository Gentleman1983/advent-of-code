package de.havox_design.aoc2020.day03

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class TobogganTrajectory(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        treeCount(data, Position2d(3, 1))

    fun processPart2(): Any =
        0L

    private fun treeCount(cells: List<String>, slope: Position2d<Int>): Int {
        val width = cells[0].length

        return (cells.indices step slope.y)
            .mapIndexed { index, y -> Position2d(index * slope.x, y) }
            .count { cells[it.y][it.x % width] == ICON_TREES }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    companion object {
        private const val ICON_TREES = '#'
    }
}
