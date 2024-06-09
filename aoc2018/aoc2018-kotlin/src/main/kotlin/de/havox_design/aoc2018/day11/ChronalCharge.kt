package de.havox_design.aoc2018.day11

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.north
import de.havox_design.aoc.utils.kotlin.model.positions.northwest
import de.havox_design.aoc.utils.kotlin.model.positions.west

class ChronalCharge(private var filename: String) {
    private val range = (1..300)

    fun processTask1(): Any =
        process() { point ->
            if (point.x < 3 || point.y < 3) emptySequence() else sequenceOf(3)
        }
            .first
            .toCoordString()

    fun processTask2(): Any =
        process()
            .let { (corner, size, _) ->
                String.format("%d,%d,%d", corner.x, corner.y, size)
            }

    private fun process(toSequence: (Position2d<Int>) -> Sequence<Int> = Position2d<Int>::squareSizesTo) =
        generateGrid(getResourceAsText(filename)[0].toInt()).entries
            .fold(mutableMapOf<Position2d<Int>, Int>()) { grid, entry ->
                grid.also { it[entry.key] = entry.value.cumulativeSum(it) }
            }
            .let {
                it.keys.asSequence().flatMap { point ->
                    toSequence(point).map { size ->
                        Triple(
                            point.northwest(size - 1), size,
                            squarePowerTo(it, point, size)
                        )
                    }
                }
                    .maxBy { (_, _, power) -> power }
            }

    private fun generateGrid(serial: Int) =
        range
            .flatMap { x -> range.map { y -> Position2d(x, y) } }
            .associateWith { Cell(it.x, it.y, serial) }

    private fun squarePowerTo(grid: Map<Position2d<Int>, Int>, pointTo: Position2d<Int>, size: Int) =
        setOf(
            pointTo,
            pointTo.northwest(size)
        )
            .sumOf {
                grid[it] ?: 0
            } - setOf(
            pointTo.north(size),
            pointTo.west(size)
        )
            .sumOf {
                grid[it] ?: 0
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private fun Position2d<Int>.squareSizesTo() =
    (1..minOf(x, y))
        .asSequence()

private fun Position2d<Int>.toCoordString(): String =
    "$x,$y"