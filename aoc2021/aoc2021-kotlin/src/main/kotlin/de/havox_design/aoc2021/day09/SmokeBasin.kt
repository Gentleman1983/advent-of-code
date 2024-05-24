package de.havox_design.aoc2021.day09

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class SmokeBasin(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val cells = data
            .map {
                it
                    .toCharArray()
                    .map { digit -> digit.digitToInt() }
            }

        return findLowPoints(cells).sumOf { cells[it] + 1 }
    }

    fun processPart2(): Any =
        0L

    private fun findLowPoints(cells: List<List<Int>>): List<Position2d<Int>> {
        return cells
            .points()
            .filter { point ->
                point
                    .neighbours()
                    .filter { it in cells }
                    .all { cells[it] > cells[point] }
            }
    }

    private fun <E> List<List<E>>.points(): ArrayList<Position2d<Int>> =
        indices
            .flatMapTo(ArrayList()) { y ->
                this[y]
                    .indices
                    .map { x -> Position2d(x, y) }
            }

    private fun Position2d<Int>.neighbours() =
        listOf(
            Position2d(x + 1, y),
            Position2d(x - 1, y),
            Position2d(x, y + 1),
            Position2d(x, y - 1)
        )

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
