package de.havox_design.aoc2021.day17

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.math.sign

class TrickShot(private var filename: String) {
    private val PATTERN = "target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)"
        .toRegex()


    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val (fromX, toX, fromY, toY) = PATTERN
            .find(data)
            ?.groupValues
            .orEmpty()
            .drop(1)
            .map { it.toInt() }
        var best = fromY

        (0..toX)
            .forEach { x ->
                (-100..100)
                    .forEach { y ->
                        val positions = generateSequence(Position2d(0, 0) to Position2d(x, y)) { (pos, speed) ->
                            val nextPos = pos + speed
                            val nextSpeed = Position2d(
                                x = speed.x - speed.x.sign,
                                y = speed.y - 1,
                            )

                            when {
                                speed.x == 0 && pos.y < fromY -> null
                                else -> nextPos to nextSpeed
                            }
                        }
                            .map { it.first }
                            .toList()

                        if (positions.any { it.x in fromX..toX && it.y in fromY..toY }) {
                            best = maxOf(best, positions.maxOf { it.y })
                        }
                    }
            }

        return best
    }

    fun processPart2(): Any =
        0L

    private operator fun Position2d<Int>.plus(other: Position2d<Int>): Position2d<Int> =
        Position2d(x + other.x, y + other.y)

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
