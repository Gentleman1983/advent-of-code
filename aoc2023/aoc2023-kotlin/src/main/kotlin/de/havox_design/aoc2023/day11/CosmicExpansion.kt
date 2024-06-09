package de.havox_design.aoc2023.day11

import de.havox_design.aoc.utils.kotlin.helpers.transpose
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.math.abs

class CosmicExpansion(private var filename: String) {
    private val ICON_GALAXY = "#"

    fun solvePart1(): Long =
        processSumOfAllDistances(getResourceAsText(filename))

    fun solvePart2(expansion: Long = 1000000): Long =
        processSumOfAllDistances(getResourceAsText(filename), expansion)

    private fun processSumOfAllDistances(input: List<String>, expansion: Long = 2): Long {
        val galaxies = input
            .flatMapIndexed { y, s ->
                s.mapIndexed { x, c -> if (c == ICON_GALAXY.first()) Position2d(x.toLong(), y.toLong()) else null }
            }
            .filterNotNull()
        val emptyRows = input
            .withIndex()
            .filter { !it.value.contains(ICON_GALAXY) }
            .map { it.index.toLong() }
        val emptyColumns = input
            .map { it.toList() }
            .transpose()
            .withIndex()
            .filter {
                !it
                    .value
                    .joinToString("") { c -> c.toString() }
                    .contains(ICON_GALAXY)
            }
            .map { it.index.toLong() }
        val expandedGalaxies = calculateUniverseExpansion(galaxies, emptyRows, emptyColumns, expansion)
        val galaxyPairs = expandedGalaxies
            .flatMapIndexed { index, galaxy ->
                expandedGalaxies
                    .drop(index + 1)
                    .map { Pair(galaxy, it) }
            }
        val distances = galaxyPairs
            .map { calculateDistance(it.first, it.second) }

        return distances.sumOf { it }
    }

    private fun calculateUniverseExpansion(
        galaxies: List<Position2d<Long>>,
        emptyRows: List<Long>,
        emptyColumns: List<Long>,
        expansion: Long = 2
    ) =
        galaxies
            .map {
                Position2d(it.x + emptyColumns.count { c -> c < it.x } * (expansion - 1),
                    it.y + emptyRows.count { r -> r < it.y } * (expansion - 1)
                )
            }

    private fun calculateDistance(galaxy1: Position2d<Long>, galaxy2: Position2d<Long>): Long {
        return abs(galaxy2.y - galaxy1.y) + abs(galaxy2.x - galaxy1.x)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}