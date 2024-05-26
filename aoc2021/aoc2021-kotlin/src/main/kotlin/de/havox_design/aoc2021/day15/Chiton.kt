package de.havox_design.aoc2021.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search.findShortestPath

class Chiton(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val nums = parseNumbers()

        return search(nums)
    }

    fun processPart2(): Any =
        0L

    private fun search(numbers: NumberGrid): Int {
        val end = Position2d(numbers[0].lastIndex, numbers.lastIndex)
        val shortestPath = findShortestPath(
            start = Position2d(0, 0),
            end = end,

            neighbours = { point -> point.neighbours().filter { it in numbers } },
            cost = { _, next -> numbers[next] },
            heuristic = { point -> (end - point).abs() },
        )

        return shortestPath.getScore()
    }

    private fun Position2d<Int>.neighbours() =
        listOf(
            Position2d(x + 1, y),
            Position2d(x - 1, y),
            Position2d(x, y + 1),
            Position2d(x, y - 1)
        )

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private operator fun Position2d<Int>.minus(other: Position2d<Int>): Position2d<Int> =
        Position2d(x - other.x, y - other.y)

    private fun Position2d<Int>.abs(): Int =
        kotlin.math.abs(x) + kotlin.math.abs(y)

    private fun parseNumbers(): NumberGrid = data
        .map { it.toCharArray().map { c -> c.digitToInt() } }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
