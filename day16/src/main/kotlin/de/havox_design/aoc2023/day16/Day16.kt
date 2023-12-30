package de.havox_design.aoc2023.day16

import de.havox_design.aoc2023.day10.Direction

class Day16(private var filename: String) {
    fun solvePart1(): Long =
        getEnergy(parseTiles(getResourceAsText(filename)), Direction.EAST, Pair(0, 0))
            .toLong()

    fun solvePart2(): Long {
        val input = getResourceAsText(filename)
        val startPoints = input
            .flatMapIndexed { row, s ->
                s
                    .withIndex()
                    .filter {
                        row == 0
                                || row == input.size - 1
                                || it.index == 0
                                || it.index == s.length - 1
                    }
                    .map { iv -> Pair(row, iv.index) }
            }
        val maxRow = startPoints
            .maxOf { it.first }
        val maxColumn = startPoints
            .maxOf { it.second }
        val results = startPoints
            .flatMap {
                when {
                    it.first == 0 && it.second == 0 -> {
                        listOf(
                            Pair(it, getEnergy(parseTiles(input), Direction.EAST, it)),
                            Pair(it, getEnergy(parseTiles(input), Direction.SOUTH, it))
                        )
                    }

                    it.first == maxRow && it.second == maxColumn -> {
                        listOf(
                            Pair(it, getEnergy(parseTiles(input), Direction.WEST, it)),
                            Pair(it, getEnergy(parseTiles(input), Direction.NORTH, it))
                        )
                    }

                    it.first == 0 -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), Direction.SOUTH, it)))
                    }

                    it.second == 0 -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), Direction.EAST, it)))
                    }

                    it.first == maxRow -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), Direction.NORTH, it)))
                    }

                    it.second == maxColumn -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), Direction.WEST, it)))
                    }

                    else -> {
                        emptyList()
                    }
                }
            }
        return results
            .maxOf { it.second }
            .toLong()
    }

    private fun getEnergy(contraption: List<List<Tile>>, enteredDirection: Direction, startPoint: Pair<Int, Int>): Int {
        traceBeam(contraption, enteredDirection, contraption[startPoint.first][startPoint.second])

        return contraption
            .flatten()
            .count { it.isEnergized() }
    }

    private fun traceBeam(contraption: List<List<Tile>>, enteredDirection: Direction, startTile: Tile) {
        val nextDirections = startTile
            .getNextDirections(enteredDirection)
        val nextLocations = nextDirections
            .map { nextLocation(startTile.row, startTile.column, it) }

        for (location in nextLocations.withIndex()) {
            when {
                (
                        location.value.first !in contraption.indices
                                || location.value.second !in contraption[startTile.row].indices
                                || nextDirections[location.index] == Direction.NONE
                        ) -> continue
            }
            traceBeam(
                contraption,
                nextDirections[location.index],
                contraption[location.value.first][location.value.second]
            )
        }
    }

    private fun nextLocation(row: Int, column: Int, direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(row - 1, column)
            Direction.SOUTH -> Pair(row + 1, column)
            Direction.EAST -> Pair(row, column + 1)
            Direction.WEST -> Pair(row, column - 1)
            else -> Pair(row, column)
        }
    }

    private fun parseTiles(input: List<String>) =
        input.mapIndexed { row, line -> line.mapIndexed { column, c -> Tile(row, column, c) } }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}