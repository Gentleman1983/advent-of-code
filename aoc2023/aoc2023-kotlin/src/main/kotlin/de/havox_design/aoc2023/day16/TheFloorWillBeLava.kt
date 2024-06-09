package de.havox_design.aoc2023.day16

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class TheFloorWillBeLava(private var filename: String) {
    fun solvePart1(): Long =
        getEnergy(parseTiles(getResourceAsText(filename)), GeoDirection.EAST, Position2d(0, 0))
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
                    .map { iv -> Position2d(iv.index, row) }
            }
        val maxRow = startPoints
            .maxOf { it.y }
        val maxColumn = startPoints
            .maxOf { it.x }
        val results = startPoints
            .flatMap {
                when {
                    it.y == 0 && it.x == 0 -> {
                        listOf(
                            Pair(it, getEnergy(parseTiles(input), GeoDirection.EAST, it)),
                            Pair(it, getEnergy(parseTiles(input), GeoDirection.SOUTH, it))
                        )
                    }

                    it.y == maxRow && it.x == maxColumn -> {
                        listOf(
                            Pair(it, getEnergy(parseTiles(input), GeoDirection.WEST, it)),
                            Pair(it, getEnergy(parseTiles(input), GeoDirection.NORTH, it))
                        )
                    }

                    it.y == 0 -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), GeoDirection.SOUTH, it)))
                    }

                    it.x == 0 -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), GeoDirection.EAST, it)))
                    }

                    it.y == maxRow -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), GeoDirection.NORTH, it)))
                    }

                    it.x == maxColumn -> {
                        listOf(Pair(it, getEnergy(parseTiles(input), GeoDirection.WEST, it)))
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

    private fun getEnergy(
        contraption: List<List<Tile>>,
        enteredDirection: GeoDirection,
        startPoint: Position2d<Int>
    ): Int {
        traceBeam(contraption, enteredDirection, contraption[startPoint.y][startPoint.x])

        return contraption
            .flatten()
            .count { it.isEnergized() }
    }

    private fun traceBeam(contraption: List<List<Tile>>, enteredDirection: GeoDirection, startTile: Tile) {
        val nextDirections = startTile
            .getNextDirections(enteredDirection)
        val nextLocations = nextDirections
            .map { nextLocation(startTile.position, it) }

        for (location in nextLocations.withIndex()) {
            when {
                (
                        location.value.y !in contraption.indices
                                || location.value.x !in contraption[startTile.position.y].indices
                                || nextDirections[location.index] == GeoDirection.NONE
                        ) -> continue
            }
            traceBeam(
                contraption,
                nextDirections[location.index],
                contraption[location.value.y][location.value.x]
            )
        }
    }

    private fun nextLocation(position: Position2d<Int>, direction: GeoDirection): Position2d<Int> {
        return when (direction) {
            GeoDirection.NORTH -> Position2d(position.x, position.y - 1)
            GeoDirection.SOUTH -> Position2d(position.x, position.y + 1)
            GeoDirection.EAST -> Position2d(position.x + 1, position.y)
            GeoDirection.WEST -> Position2d(position.x - 1, position.y)
            else -> Position2d(position.x, position.y)
        }
    }

    private fun parseTiles(input: List<String>) =
        input
            .mapIndexed { row, line ->
                line
                    .mapIndexed { column, c ->
                        Tile(Position2d(column, row), c)
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