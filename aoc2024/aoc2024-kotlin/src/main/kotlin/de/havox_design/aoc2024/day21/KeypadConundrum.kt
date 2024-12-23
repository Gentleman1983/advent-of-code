package de.havox_design.aoc2024.day21

import de.havox_design.aoc.utils.kotlin.model.chargrid.CharGrid
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.math.abs

class KeypadConundrum(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val cache = mutableMapOf<DpParams, Long>()

    fun processPart1(): Any =
        calculateComplexity(data, 2)

    fun processPart2(): Any =
        calculateComplexity(data, 25)

    private fun calculateComplexity(codes: List<String>, depth: Int): Long =
        codes
            .sumOf { code ->
                val numericPart = code
                    .filter { it.isDigit() }
                    .toLong()
                dp(DpParams(code, depth, 0)) * numericPart
            }

    private fun dp(params: DpParams): Long {
        return cache
            .getOrPut(params) {
                val (sequence, maxDepth, currentDepth) = params
                val keypad = if (currentDepth == 0) {
                    NUMERIC_KEYPAD
                } else {
                    DIRECTIONAL_KEYPAD
                }
                var pos = keypad
                    .findChar(KEY_ACTIVATE)!!
                var sum = 0L

                sequence
                    .forEach { char ->
                        val next = keypad
                            .findChar(char)!!
                        val paths = generatePaths(pos, next)
                            .filter { path ->
                                keypad
                                    .isValidPath(path, pos)
                            }
                            .map { "${it}${KEY_ACTIVATE}" }
                            .ifEmpty { listOf("$KEY_ACTIVATE") }

                        sum += if (currentDepth == maxDepth) {
                            paths
                                .minOf { it.length }
                                .toLong()
                        } else {
                            paths
                                .minOf { dp(DpParams(it, maxDepth, currentDepth + 1)) }
                        }

                        pos = next
                    }

                sum
            }
    }

    private fun CharGrid.isValidPath(path: String, start: Position2d<Int>): Boolean {
        var current = start

        return path
            .all { dir ->
                current += when (dir) {
                    '^' -> Position2d(0, 1)
                    'v' -> Position2d(0, -1)
                    '<' -> Position2d(-1, 0)
                    '>' -> Position2d(1, 0)
                    else -> error("Invalid direction $dir")
                }

                this.isValidPosition(current) && this[current] != '.'
            }
    }

    private fun generatePaths(from: Position2d<Int>, to: Position2d<Int>): List<String> {
        val dx = to.x - from.x
        val dy = to.y - from.y

        return generateDirectionalSequence(dx, dy)
    }

    private fun generateDirectionalSequence(dx: Int, dy: Int): List<String> {
        val result = mutableListOf<String>()

        generate(abs(dx), abs(dy), "", dx, dy, result)

        return result.distinct()
    }

    private fun generate(
        remainingH: Int,
        remainingV: Int,
        current: String,
        dx: Int,
        dy: Int,
        result: MutableList<String>
    ) {
        if (remainingH == 0 && remainingV == 0) {
            result.add(current)

            return
        }

        if (remainingH != 0) {
            generate(remainingH - 1, remainingV, current + if (dx < 0) "<" else ">", dx, dy, result)
        }

        if (remainingV != 0) {
            generate(remainingH, remainingV - 1, current + if (dy < 0) "v" else "^", dx, dy, result)
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val KEY_ACTIVATE = 'A'

        private val DIRECTIONAL_KEYPAD = CharGrid
            .fromString(
                """
                .^A
                <v>
                """
                    .trimIndent()
            )

        private val NUMERIC_KEYPAD = CharGrid
            .fromString(
                """
                789
                456
                123
                .0A
                """
                    .trimIndent()
            )

    }
}

private fun CharGrid.findChar(target: Char): Position2d<Int>? =
    firstOrNull { it.value == target }
        ?.point
