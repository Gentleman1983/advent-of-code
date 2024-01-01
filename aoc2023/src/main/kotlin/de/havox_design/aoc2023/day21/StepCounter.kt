package de.havox_design.aoc2023.day21

import kotlin.math.pow

class StepCounter(private var filename: String) {
    private val ICON_ROCK = '#'
    private val ICON_START = 'S'

    fun solvePart1(steps: Int = 64): Long {
        val rocks = HashSet<Pair<Int, Int>>()
        var start = Pair(0, 0)

        for ((row, inputRow) in getResourceAsText(filename).withIndex()) {
            inputRow.forEachIndexed { col, ch ->
                when (ch) {
                    ICON_START -> start = Pair(col, row)
                    ICON_ROCK -> rocks.add(Pair(col, row))
                }
            }
        }

        var reachablePlots = HashSet<Pair<Int, Int>>()
        reachablePlots.add(start)
        for (i in 1..steps) {
            val next = HashSet<Pair<Int, Int>>()

            for (position in reachablePlots) {
                move(position, Direction.UP, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.RIGHT, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.DOWN, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.LEFT, rocks)
                    ?.let { next.add(it) }
            }

            reachablePlots = next
        }

        return reachablePlots
            .size
            .toLong()
    }

    fun solvePart2(steps: Long = 26501365L): Long {
        val rocks = HashSet<Pair<Int, Int>>()
        var start = Pair(0, 0)
        var rows = 0

        for (inputRows in getResourceAsText(filename)) {
            inputRows.forEachIndexed { col, ch ->
                val row = rows

                when (ch) {
                    ICON_START -> start = Pair(col, row)
                    ICON_ROCK -> rocks.add(Pair(col, row))
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
        start: Pair<Int, Int>,
        rows: Int,
        rocks: HashSet<Pair<Int, Int>>,
        evenGrids: Long,
        grids: Long
    ) = oddGrids * reachable(start, rows * 2 + 1, rocks, rows) +
            evenGrids * reachable(start, rows * 2, rocks, rows) +
            reachable(Pair(start.first, rows - 1), rows - 1, rocks, rows) +
            reachable(Pair(0, start.second), rows - 1, rocks, rows) +
            reachable(Pair(start.first, 0), rows - 1, rocks, rows) +
            reachable(Pair(rows - 1, start.second), rows - 1, rocks, rows) +
            (
                    (grids + 1) * (reachable(Pair(0, rows - 1), rows / 2 - 1, rocks, rows) +
                            reachable(Pair(rows - 1, rows - 1), rows / 2 - 1, rocks, rows) +
                            reachable(Pair(0, 0), rows / 2 - 1, rocks, rows) +
                            reachable(Pair(rows - 1, 0), rows / 2 - 1, rocks, rows))
                    ) +
            (
                    grids * (reachable(Pair(0, rows - 1), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Pair(rows - 1, rows - 1), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Pair(0, 0), rows * 3 / 2 - 1, rocks, rows) +
                            reachable(Pair(rows - 1, 0), rows * 3 / 2 - 1, rocks, rows))
                    )

    private fun move(
        position: Pair<Int, Int>,
        direction: Direction,
        rocks: HashSet<Pair<Int, Int>>
    ): Pair<Int, Int>? =
        when {
            !rocks.contains(
                Pair(
                    position.first + direction.direction.first,
                    position.second + direction.direction.second
                )
            ) -> {
                Pair(
                    position.first + direction.direction.first,
                    position.second + direction.direction.second
                )
            }

            else -> {
                null
            }
        }

    private fun reachable(from: Pair<Int, Int>, steps: Int, rocks: HashSet<Pair<Int, Int>>, rows: Int): Int {
        var reachablePlots = HashSet<Pair<Int, Int>>()
        reachablePlots.add(from)
        for (i in 1..steps) {
            val next = HashSet<Pair<Int, Int>>()
            fun move(p: Pair<Int, Int>, dir: Pair<Int, Int>) {
                if (!rocks.contains(Pair(p.first + dir.first, p.second + dir.second))) {
                    next.add(Pair(p.first + dir.first, p.second + dir.second))
                }
            }
            for (position in reachablePlots) {
                move(position, Direction.UP, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.RIGHT, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.DOWN, rocks)
                    ?.let { next.add(it) }
                move(position, Direction.LEFT, rocks)
                    ?.let { next.add(it) }
            }
            reachablePlots = next
        }
        return reachablePlots.count { it.first >= 0 && it.second >= 0 && it.first < rows && it.second < rows }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
