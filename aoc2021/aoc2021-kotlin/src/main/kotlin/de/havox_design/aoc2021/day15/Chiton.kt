package de.havox_design.aoc2021.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.abs
import de.havox_design.aoc.utils.kotlin.model.positions.minus
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours
import de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search.findShortestPath

class Chiton(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val nums = parseNumbers()

        return search(nums)
    }

    fun processPart2(): Any {
        val inputNumbers = parseNumbers()
        val firstRowBlocks = (0 until 4)
            .runningFold(inputNumbers) { acc, _ -> increaseByOne(acc) }
        val firstRow = combineHorizontal(firstRowBlocks)
        val rows = (0 until 4).runningFold(firstRow) { acc, _ -> increaseByOne(acc) }
        val finalNumbers = combineVertical(rows)

        return search(finalNumbers)
    }

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

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private fun parseNumbers(): NumberGrid =
        data
            .map { it.toCharArray().map { c -> c.digitToInt() } }

    private fun increaseByOne(nums: NumberGrid) =
        nums
            .map { line -> line.map { it.mod(9) + 1 } }

    private fun combineHorizontal(blocks: List<NumberGrid>) =
        blocks
            .first()
            .indices
            .map { row -> createRow(row, blocks) }

    private fun createRow(rowIndex: Int, blocks: List<NumberGrid>) =
        blocks
            .map { it[rowIndex] }
            .reduce { acc, list -> acc + list }

    private fun combineVertical(rows: List<NumberGrid>) =
        rows
            .reduce(NumberGrid::plus)

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
