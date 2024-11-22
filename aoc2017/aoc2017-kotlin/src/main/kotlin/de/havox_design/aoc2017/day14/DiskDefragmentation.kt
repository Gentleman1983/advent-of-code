package de.havox_design.aoc2017.day14

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours
import de.havox_design.aoc2017.day10.knotHash

class DiskDefragmentation(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .toGrid()
            .sumOf { hash ->
                hash
                    .chunked(7)
                    .sumOf { hex ->
                        hex
                            .toInt(16)
                            .countOneBits()
                    }
            }

    fun processPart2(): Any {
        var blocks = 0
        val points = data
            .toGrid()
            .flatMapIndexed { y, line ->
                line
                    .toBin()
                    .withIndex()
                    .fold(mutableSetOf<Position2d<Int>>()) { points, (x, char) ->
                        points
                            .apply {
                                if (char == '1') {
                                    add(Position2d(x, y))
                                }
                            }
                    }
            }
            .toMutableSet()

        while (points.isNotEmpty()) {
            points
                .removeAll(extractBlock(points))
            blocks++
        }

        return blocks
    }

    private fun extractBlock(points: Set<Position2d<Int>>): Set<Position2d<Int>> {
        val start = points.first()
        val queue = ArrayDeque(listOf(start))
        val block = hashSetOf(start)

        while (queue.isNotEmpty()) {
            queue.removeFirst()
                .neighbours()
                .filter { it in points && it !in block }
                .let { neighbours ->
                    queue.addAll(neighbours)
                    block.addAll(neighbours)
                }
        }

        return block
    }

    private fun String.toGrid() =
        (0 until 128)
            .map { knotHash("$this-$it") }

    private fun String.toBin() =
        toBigInteger(16)
            .toString(2)
            .padStart(128, '0')

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
