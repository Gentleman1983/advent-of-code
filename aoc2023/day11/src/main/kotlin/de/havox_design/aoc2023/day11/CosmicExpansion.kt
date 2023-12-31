package de.havox_design.aoc2023.day11

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
                s.mapIndexed { x, c -> if (c == ICON_GALAXY.first()) Pair(y.toLong(), x.toLong()) else null }
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
        galaxies: List<Pair<Long, Long>>,
        emptyRows: List<Long>,
        emptyColumns: List<Long>,
        expansion: Long = 2
    ) =
        galaxies
            .map {
                Pair(it.first + emptyRows.count { r -> r < it.first } * (expansion - 1),
                    it.second + emptyColumns.count { c -> c < it.second } * (expansion - 1))
            }

    private fun calculateDistance(galaxy1: Pair<Long, Long>, galaxy2: Pair<Long, Long>): Long {
        return abs(galaxy2.first - galaxy1.first) + abs(galaxy2.second - galaxy1.second)
    }

    private fun <T> List<List<T>>.transpose(): List<List<T>> {
        return when {
            this.isEmpty() -> this
            else -> (this[0].indices)
                .map { i ->
                    (this.indices)
                        .map { j -> this[j][i] }
                }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}