package de.havox_design.aoc2023.day18

import de.havox_design.aoc2023.day17.*
import kotlin.math.abs

class LavaductLagoon(private var filename: String) {
    private val BLACK = "#000000"
    private val ICON_UP = "U"
    private val ICON_DOWN = "D"
    private val ICON_LEFT = "L"
    private val ICON_RIGHT = "R"
    private val ELEMENT_DELIMITER = " "

    fun solvePart1(): Long =
        mutableMapOf(origin to BLACK)
            .processInstructions(origin, parseDigInstructions())
            .calculateTrench()

    fun solvePart2(): Long {
        val polygon = parseDigInstructions()
            .map { it.transformInstruction() }
            .runningFold(origin) { current, instruction ->
                when (instruction.direction) {
                    FourDirectionFlipped.RIGHT -> Coordinate(current.x + instruction.amount, current.y)
                    FourDirectionFlipped.LEFT -> Coordinate(current.x - instruction.amount, current.y)
                    FourDirectionFlipped.DOWN -> Coordinate(current.x, current.y + instruction.amount)
                    FourDirectionFlipped.UP -> Coordinate(current.x, current.y - instruction.amount)
                }
            }
            .toList()
        val area = polygon.polygonArea()
        val boundary = polygon.pointsOnBoundary()

        return (area - boundary / 2 + 1).toLong() + boundary
    }

    private fun MutableMap<Coordinate, String>.processInstructions(
        start: Coordinate,
        instructions: List<DigInstruction>
    ): MutableMap<Coordinate, String> {
        var current = start

        instructions
            .forEach { instruction ->
                repeat(instruction.amount) {
                    current = instruction.direction + current
                    set(current, instruction.colorCode)
                }
            }

        return this
    }

    private fun MutableMap<Coordinate, String>.calculateTrench(): Long {
        val xRange = xRange()
            .let { (a, b) ->
                a..b
            }
        val yRange = yRange()
            .let { (a, b) ->
                a..b
            }
        val connectNorth = keys
            .filter { FourDirectionFlipped.UP + it in this }
            .toSet()
        var count = 0L

        yRange.forEach { y ->
            var border = 0

            xRange.forEach { x ->
                val coordinate = Coordinate(x, y)

                border += when {
                    coordinate in connectNorth -> 1
                    else -> 0
                }
                when {
                    containsKey(coordinate) -> count++
                    border % 2 == 1 -> count++
                }
            }
        }

        return count
    }

    private fun List<Coordinate>.polygonArea(): Double =
        abs((1..<size)
            .sumOf { value -> crossProduct(get(value), get(value - 1)) } / 2.0)

    private fun crossProduct(a: Coordinate, b: Coordinate) =
        a.x.toLong() * b.y.toLong() - b.x.toLong() * a.y.toLong()

    private fun List<Coordinate>.pointsOnBoundary(): Long =
        zipWithNext()
            .sumOf { (a, b) ->
                val delta = a - b
                abs(gcd(delta.x.toLong(), delta.y.toLong()))
            }
            .let {
                val delta = last() - first()
                abs(gcd(delta.x.toLong(), delta.y.toLong())) + it
            }

    private fun gcd(number1: Long, number2: Long): Long =
        when (number2) {
            0L -> number1
            else -> gcd(number2, number1 % number2)
        }

    private fun parseDigInstructions() =
        getResourceAsText(filename)
            .map { row ->
                row
                    .split(ELEMENT_DELIMITER, limit = 3)
                    .let { (direction, steps, color) ->
                        DigInstruction(
                            when (direction) {
                                ICON_RIGHT -> FourDirectionFlipped.RIGHT
                                ICON_LEFT -> FourDirectionFlipped.LEFT
                                ICON_UP -> FourDirectionFlipped.UP
                                ICON_DOWN -> FourDirectionFlipped.DOWN
                                else -> error("Invalid input : $direction $steps $color")
                            },
                            steps.toInt(),
                            color.drop(1).dropLast(1)
                        )
                    }
            }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}