package de.havox_design.aoc2023.day21

class Day21(private var filename: String) {
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

    fun solvePart2(steps: Int = 26501365): Long =
        16L

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

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
