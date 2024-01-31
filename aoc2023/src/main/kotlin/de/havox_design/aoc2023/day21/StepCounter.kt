package de.havox_design.aoc2023.day21

import de.havox_design.aoc.utils.kotlin.model.directions.UDLRDirection
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.math.pow

class StepCounter(private var filename: String) {
    private val ICON_ROCK = '#'
    private val ICON_START = 'S'

    fun solvePart1(steps: Int = 64): Long {
        val rocks = HashSet<Position2d<Int>>()
        var start = Position2d(0, 0)

        for ((row, inputRow) in getResourceAsText(filename).withIndex()) {
            inputRow.forEachIndexed { col, ch ->
                when (ch) {
                    ICON_START -> start = Position2d(col, row)
                    ICON_ROCK -> rocks.add(Position2d(col, row))
                }
            }
        }

        var reachablePlots = HashSet<Position2d<Int>>()
        reachablePlots.add(start)
        for (i in 1..steps) {
            val next = HashSet<Position2d<Int>>()

            for (position in reachablePlots) {
                move(position, UDLRDirection.UP, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.RIGHT, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.DOWN, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.LEFT, rocks)
                    ?.let { next.add(it) }
            }

            reachablePlots = next
        }

        return reachablePlots
            .size
            .toLong()
    }

    fun solvePart2(steps: Long = 26501365L): Long {
        val rocks = HashSet<Position2d<Int>>()
        var start = Position2d(0, 0)
        var rows = 0

        for (inputRows in getResourceAsText(filename)) {
            inputRows.forEachIndexed { col, ch ->
                val row = rows

                when (ch) {
                    ICON_START -> start = Position2d(col, row)
                    ICON_ROCK -> rocks.add(Position2d(col, row))
                }
            }

            rows++
        }

        val grids = steps / rows - 1
        val oddGrids = (grids / 2 * 2 + 1)
            .toDouble()
            .pow(2)
            .toLong()
        val evenGrids = ((grids + 1) / 2 * 2)
            .toDouble()
            .pow(2)
            .toLong()

        return sumUpReachablePlots(oddGrids, start, rows, rocks, evenGrids, grids)
    }

    private fun sumUpReachablePlots(
        oddGrids: Long,
        start: Position2d<Int>,
        rows: Int,
        rocks: HashSet<Position2d<Int>>,
        evenGrids: Long,
        grids: Long
    ) = oddGrids * reachable(start, rows * 2 + 1, rocks, rows) +
            evenGrids * reachable(start, rows * 2, rocks, rows) +
            reachable(Position2d(start.x, rows - 1), rows - 1, rocks, rows) +
            reachable(Position2d(0, start.y), rows - 1, rocks, rows) +
            reachable(Position2d(start.x, 0), rows - 1, rocks, rows) +
            reachable(Position2d(rows - 1, start.y), rows - 1, rocks, rows) +
            (
                    (grids + 1) * (reachable(Position2d(0, rows - 1), rows / 2 - 1, rocks, rows) +
                            reachable(Position2d(rows - 1, rows - 1), rows / 2 - 1, rocks, rows) +
                            reachable(Position2d(0, 0), rows / 2 - 1, rocks, rows) +
                            reachable(Position2d(rows - 1, 0), rows / 2 - 1, rocks, rows))
                    ) +
            (
                    grids * (reachable(Position2d(0, rows - 1), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Position2d(rows - 1, rows - 1), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Position2d(0, 0), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Position2d(rows - 1, 0), rows * 3 / 2 - 1, rocks, rows))
                    )

    private fun move(
        position: Position2d<Int>,
        direction: UDLRDirection,
        rocks: HashSet<Position2d<Int>>
    ): Position2d<Int>? =
        when {
            !rocks.contains(
                Position2d(
                    position.x + direction.direction.x,
                    position.y + direction.direction.y
                )
            ) -> {
                Position2d(
                    position.x + direction.direction.x,
                    position.y + direction.direction.y
                )
            }

            else -> {
                null
            }
        }

    private fun reachable(from: Position2d<Int>, steps: Int, rocks: HashSet<Position2d<Int>>, rows: Int): Int {
        var reachablePlots = HashSet<Position2d<Int>>()
        reachablePlots.add(from)
        for (i in 1..steps) {
            val next = HashSet<Position2d<Int>>()
            fun move(p: Position2d<Int>, dir: Position2d<Int>) {
                if (!rocks.contains(Position2d(p.x + dir.x, p.y + dir.y))) {
                    next.add(Position2d(p.x + dir.x, p.y + dir.y))
                }
            }
            for (position in reachablePlots) {
                move(position, UDLRDirection.UP, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.RIGHT, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.DOWN, rocks)
                    ?.let { next.add(it) }
                move(position, UDLRDirection.LEFT, rocks)
                    ?.let { next.add(it) }
            }
            reachablePlots = next
        }
        return reachablePlots.count { it.x >= 0 && it.y >= 0 && it.x < rows && it.y < rows }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
