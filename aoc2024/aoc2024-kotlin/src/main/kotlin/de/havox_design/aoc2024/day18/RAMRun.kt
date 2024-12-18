package de.havox_design.aoc2024.day18

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours
import java.util.PriorityQueue

class RAMRun(private var filename: String) {
    private val data = parseData(getResourceAsText(filename))

    fun processPart1(fieldX: Int = 70, fieldY: Int = 70, readBytes: Int = 1024): Any =
        findPath(
            fieldX,
            fieldY,
            data
                .take(readBytes)
                .toSet()
        ) ?: 0

    fun processPart2(fieldX: Int = 70, fieldY: Int = 70): Any =
        data
            .indices
            .takeWhile {
                findPath(
                    fieldX = fieldX,
                    fieldY = fieldY,
                    coordinates = data
                        .take(it)
                        .toSet()
                ) != null
            }
            .last()
            .let {
                val coordinate = data[it]

                "${coordinate.x},${coordinate.y}"
            }

    private fun findPath(fieldX: Int, fieldY: Int, coordinates: Set<Position2d<Int>>): Int? {
        val start = Position2d(0, 0)
        val field = Field(x = fieldX + 1, y = fieldY + 1) { MEMORY_OKAY }
            .insertAt(coordinates.associateWith { MEMORY_CORRUPTED })
        val end = Position2d(field.width - 1, field.height - 1)
        val queue = PriorityQueue<Pair<Position2d<Int>, Int>>(compareBy { it.second })
        val seen = mutableSetOf<Position2d<Int>>()

        queue
            .add(start to 0)

        while (queue.isNotEmpty()) {
            val (position, steps) = queue
                .poll()

            if (!seen.add(position)) {
                continue
            }

            if (position == end) {
                return steps
            }

            position
                .neighbours()
                .filter { it in field && field[it] != MEMORY_CORRUPTED }
                .forEach {
                    queue
                        .add(it to steps + 1)
                }
        }

        return null
    }

    private fun String.extractInts() =
        if (isEmpty()) {
            emptyList()
        } else {
            Regex("-?\\d+")
                .findAll(this)
                .map { it.value }
                .toList()
                .map { it.toInt() }
        }

    private fun parseData(input: List<String>): List<Position2d<Int>> =
        input
            .map { row ->
                val (x, y) = row.extractInts()
                Position2d(x = x, y = y)
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val MEMORY_CORRUPTED = '#'
        private const val MEMORY_OKAY = '.'
    }
}
