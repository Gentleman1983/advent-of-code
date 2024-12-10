package de.havox_design.aoc2024.day10

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class HoofIt(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val map = parseMap()

    fun processPart1(): Any =
        solve()
            .first

    fun processPart2(): Any =
        solve()
            .second

    private fun solve(): Pair<Any, Any> {
        var totalScore = 0
        var totalRating = 0

        map
            .forEachIndexed { y, row ->
                row
                    .forEachIndexed { x, column ->
                        if (column == 0) {
                            val reachablePicks = getReachablePeaks(Position2d(x, y))

                            totalScore += reachablePicks
                                .distinct()
                                .size
                            totalRating += reachablePicks
                                .size
                        }
                    }
            }

        return Pair(totalScore, totalRating)
    }

    private fun getReachablePeaks(start: Position2d<Int>): List<Position2d<Int>> {
        val currentHeight = map
            .getOrNull(start)
            ?: return emptyList()

        if (currentHeight == 9) {
            return listOf(start)
        }

        return sequenceOf(
            Position2d(start.x + 1, start.y),
            Position2d(start.x - 1, start.y),
            Position2d(start.x, start.y + 1),
            Position2d(start.x, start.y - 1),
        )
            .filter { map.getOrNull(it) == currentHeight + 1 }
            .map(::getReachablePeaks)
            .flatten()
            .toList()
    }

    private fun parseMap() =
        data
            .filter(String::isNotEmpty)
            .map { row ->
                row.map { it.digitToIntOrNull() ?: -1 }
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    private fun <T : Any?> List<List<T>>.getOrNull(position: Position2d<Int>): T? =
        this
            .getOrNull(position.y)
            ?.getOrNull(position.x)
}
