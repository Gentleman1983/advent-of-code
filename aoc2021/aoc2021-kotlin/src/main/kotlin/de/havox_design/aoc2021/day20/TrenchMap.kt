package de.havox_design.aoc2021.day20

import de.havox_design.aoc.utils.kotlin.helpers.digitsToInt
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.diagonalNeighbours
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours

class TrenchMap(private var filename: String) {
    private val ICON_BLACK = '.'
    private val ID_BLACK = 0
    private val ID_WHITE = 1
    private val data = getResourceAsText(filename)
    private val pointComparator: Comparator<Position2d<Int>> =
        Comparator
            .comparing<Position2d<Int>?, Int?> { point -> point.y }
            .thenComparing { point -> point.x }

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        solve(50)

    private fun solve(iterations: Int = 2): Int {
        val algorithm = data
            .first()
            .map {
                if (it == ICON_BLACK) {
                    ID_BLACK
                } else {
                    ID_WHITE
                }
            }
        val image = data
            .drop(2)
            .map { line ->
                line
                    .map {
                        if (it == ICON_BLACK) {
                            ID_BLACK
                        } else {
                            ID_WHITE
                        }
                    }
            }

        return (0 until iterations)
            .fold(image to 0) { (image, colour), _ ->
                runAlgorithm(algorithm, pad(image, colour), colour)
            }
            .first
            .sumOf { it.sum() }
    }

    private fun pad(image: List<List<Int>>, padValue: Int): List<List<Int>> {
        val padAmount = 1
        val newWidth = image.first().size + padAmount * 2
        val blankRow = Array(newWidth) { padValue }.toList()
        val blankSegment = Array(padAmount) { padValue }.toList()

        return buildList {
            repeat(padAmount) {
                add(blankRow)
            }

            for (row in image) {
                add(blankSegment + row + blankSegment)
            }

            repeat(padAmount) {
                add(blankRow)
            }
        }
    }

    private fun runAlgorithm(
        algorithm: List<Int>,
        image: List<List<Int>>,
        infiniteColour: Int
    ): Pair<List<List<Int>>, Int> {
        val rows = image
            .points()
            .groupBy { it.y }
        val nextInfiniteColour =
            if (infiniteColour == 0) {
                algorithm.first()
            } else {
                algorithm.last()
            }

        return buildList {
            for (row in rows.values) {
                add(buildRow(row, image, algorithm, infiniteColour))
            }
        } to nextInfiniteColour
    }

    private fun buildRow(
        row: List<Position2d<Int>>,
        image: List<List<Int>>,
        algorithm: List<Int>,
        infiniteColor: Int
    ): List<Int> =
        buildList {
            addAll(row.map { point -> algorithm[transformPoint(point, image, infiniteColor)] })
        }

    private fun transformPoint(point: Position2d<Int>, image: List<List<Int>>, infiniteColor: Int) =
        (point.neighbours() + point.diagonalNeighbours() + point)
            .sortedWith(pointComparator)
            .map { neighbour ->
                if (neighbour in image) {
                    image[neighbour]
                } else {
                    infiniteColor
                }
            }
            .digitsToInt(2)

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private fun <E> List<List<E>>.points(): ArrayList<Position2d<Int>> {
        return indices
            .flatMapTo(ArrayList()) { y ->
                this[y]
                    .indices
                    .map { x -> Position2d(x, y) }
            }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
