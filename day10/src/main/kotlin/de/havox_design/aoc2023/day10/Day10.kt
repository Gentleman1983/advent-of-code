package de.havox_design.aoc2023.day10

class Day10(private var filename: String) {
    private val ICON_START = 'S'
    private val ICON_VERTICAL_PIPE = '|'
    private val ICON_HORIZONTAL_PIPE = '-'
    private val ICON_NORTH_EAST_BEND = 'L'
    private val ICON_NORTH_WEST_BEND = 'J'
    private val ICON_SOUTH_WEST_BEND = '7'
    private val ICON_SOUTH_EAST_BEND = 'F'
    private val ICON_FREE_GROUND = '.'

    fun solvePart1(): Long =
        solvePart1(
            getResourceAsText(filename)
                .map { row -> row.toCharArray().toList() }
        )

    fun solvePart2(): Long =
        0L

    private fun solvePart1(maze: List<List<Char>>): Long {
        var start: Pair<Int, Int>? = null

        out@ for (row in maze.indices) {
            for (col in maze[row].indices) {
                if (maze[row][col] == ICON_START) {
                    start = Pair(row, col)
                    break@out
                }
            }
        }

        val loop = getLoop(start!!, maze)

        return (loop.size / 2).toLong()
    }

    private fun getLoop(start: Pair<Int, Int>, maze: List<List<Char>>): List<Pair<Int, Int>> {
        val loop = mutableListOf(start)

        do {
            val adjacentCells = maze
                .connectedCells(loop.last())
                .filter { !loop.contains(it) }
                .take(1)

            loop.addAll(adjacentCells)
        } while (adjacentCells.isNotEmpty())

        return loop
    }

    private fun List<List<Char>>.connectedCells(currentLocation: Pair<Int, Int>): List<Pair<Int, Int>> {
        return when (this[currentLocation.first][currentLocation.second]) {
            ICON_VERTICAL_PIPE -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_HORIZONTAL_PIPE -> listOf(
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.EAST)
            )

            ICON_NORTH_EAST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.EAST)
            )

            ICON_NORTH_WEST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.WEST)
            )

            ICON_SOUTH_WEST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_SOUTH_EAST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.EAST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_START -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.EAST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )
                .filter { (it.first in indices) && (it.second in this[it.first].indices) }
                .filter { this.connectedCells(it).any { next -> next == currentLocation } }

            else -> emptyList()
        }.filter { (it.first in indices) && (it.second in this[it.first].indices) }
    }

    private fun Pair<Int, Int>.getAdjacentField(direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(this.first - 1, this.second)
            Direction.SOUTH -> Pair(this.first + 1, this.second)
            Direction.WEST -> Pair(this.first, this.second - 1)
            Direction.EAST -> Pair(this.first, this.second + 1)
            Direction.NONE -> this
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}