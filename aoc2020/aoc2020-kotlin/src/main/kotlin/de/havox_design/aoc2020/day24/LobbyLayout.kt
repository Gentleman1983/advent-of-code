package de.havox_design.aoc2020.day24

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d

class LobbyLayout(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val PATTERN = "(nw|ne|w|e|sw|se)".toRegex()
    private val DIRS = mapOf(
        "nw" to Position3d(0, 1, -1),
        "ne" to Position3d(1, 0, -1),
        "w" to Position3d(-1, 1, 0),
        "e" to Position3d(1, -1, 0),
        "sw" to Position3d(-1, 0, 1),
        "se" to Position3d(0, -1, 1),
    )

    fun processPart1(): Any {
        val items = data
            .map { line ->
                PATTERN
                    .findAll(line)
                    .map { it.groupValues[1] }
                    .toList()
            }

        val tiles = items.map { steps ->
            steps
                .fold(Position3d(0, 0, 0)) { acc, value -> acc + DIRS.getValue(value) }
        }

        return tiles
            .groupBy { it }
            .count { it.value.size % 2 == 1 }
    }

    fun processPart2(): Any {
        val items = data
            .map { line ->
                PATTERN
                    .findAll(line)
                    .map { it.groupValues[1] }
                    .toList()
            }

        val start = items
            .map { steps ->
                steps
                    .fold(Position3d(0, 0, 0)) { acc, value -> acc + DIRS.getValue(value) }
            }
            .groupBy { it }
            .filterValues { it.size % 2 == 1 }
            .keys

        val result = (0..<100)
            .fold(start) { prev, _ ->
                val neighbors = prev
                    .flatMap { cell -> DIRS.values.map { it + cell } }
                    .groupingBy { it }
                    .eachCount()

                return@fold (prev.filter { neighbors[it] in 1..2 } + (neighbors.filterValues { it == 2 }.keys - prev)).toSet()
            }

        return result.size
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private operator fun Position3d<Int>.plus(other: Position3d<Int>) =
    Position3d(x + other.x, y + other.y, z + other.z)
