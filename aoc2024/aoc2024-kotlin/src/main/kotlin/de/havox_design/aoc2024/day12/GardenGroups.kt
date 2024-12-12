package de.havox_design.aoc2024.day12

import de.havox_design.aoc.utils.kotlin.model.field.Field
import de.havox_design.aoc.utils.kotlin.model.field.toField
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours

class GardenGroups(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any =
        calculatePrice()
            .first

    fun processPart2(): Any =
        calculatePrice()
            .second

    private fun calculatePrice(): Pair<Int, Int> {
        val flowersToCheck = data
            .allPositions
            .toMutableSet()
        var totalPrice = 0
        var totalDiscountedPrice = 0

        while (flowersToCheck.isNotEmpty()) {
            val position = flowersToCheck
                .first()
            val flower = data[position]

            val (area, fullPerimeter, seen) = calculateAreaAndPerimeter(position, flower)
            flowersToCheck
                .removeAll(seen)

            totalPrice += area * fullPerimeter
            totalDiscountedPrice += area * findNumberOfSides(seen, flower)
        }

        return totalPrice to totalDiscountedPrice
    }

    private fun calculateAreaAndPerimeter(        start: Position2d<Int>,        flower: String    ): Triple<Int, Int, Set<Position2d<Int>>> {
        val flowersToProcess = ArrayDeque<Position2d<Int>>()
            .apply { add(start) }
        val seen = mutableSetOf<Position2d<Int>>()
        var area = 0
        var fullPerimeter = 0

        while (flowersToProcess.isNotEmpty()) {
            val current = flowersToProcess
                .removeFirst()

            if (current in seen) {
                continue
            }

            area++

            seen
                .add(current)

            current
                .neighbours()
                .forEach { neighbor ->
                if (neighbor !in data || data[neighbor] != flower) {
                    fullPerimeter++
                } else if (neighbor !in seen) {
                    flowersToProcess
                        .add(neighbor)
                }
            }
        }
        return Triple(area, fullPerimeter, seen)
    }

    private fun findNumberOfSides(flowerGroup: Set<Position2d<Int>>, flower: String): Int {
        val topLeft = Position2d(0, 0)
        val topRight = Position2d(1, 0)
        val bottomLeft = Position2d(0, 1)
        val bottomRight = Position2d(1, 1)

        val bitMask2x2 = setOf(topLeft, topRight, bottomLeft, bottomRight)

        val oneEdgeBitMasks = setOf(
            setOf(topLeft),
            setOf(topRight),
            setOf(bottomLeft),
            setOf(bottomRight),
            setOf(topLeft, topRight, bottomLeft),
            setOf(topRight, bottomLeft, bottomRight),
            setOf(bottomLeft, bottomRight, topLeft),
            setOf(bottomRight, topLeft, topRight),
        )

        val twoEdgeBitMasks = setOf(
            setOf(topLeft, bottomRight),
            setOf(topRight, bottomLeft),
        )

        val y0 = flowerGroup.minOf { it.y }
        val y1 = flowerGroup.maxOf { it.y }
        val x0 = flowerGroup.minOf { it.x }
        val x1 = flowerGroup.maxOf { it.x }

        val offset = Position2d(x0 - 1, y0 - 1)

        val transformedField = Field(x1 - x0 + 3, y1 - y0 + 3) {
            "."
        }
            .insertAt(flowerGroup.map { it - offset }.associateWith { flower })

        var sides = 0

        (0 until (transformedField.numberOfY - 1)).forEach { y ->
            (0 until (transformedField.numberOfX - 1)).forEach { x ->
                bitMask2x2
                    .map { it + Position2d(x, y) }
                    .filter { transformedField[it] == flower }
                    .map { it - Position2d(x, y) }
                    .toSet()
                    .let {
                        when (it) {
                            in oneEdgeBitMasks -> sides++
                            in twoEdgeBitMasks -> sides += 2
                        }
                    }
            }
        }

        return sides
    }

    private fun List<String>.toGrid() =
        this.map { row ->
            row
                .split("")
                .let {
                    it
                        .filter { it.isNotBlank() }
                }
        }

    private fun parseInput(input: List<String>) =
        input
            .toGrid()
            .toField()

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private operator fun Position2d<Int>.plus(other: Position2d<Int>) = Position2d(x + other.x, y + other.y)
private operator fun Position2d<Int>.minus(other: Position2d<Int>) = this + other * -1
private operator fun Position2d<Int>.times(factor: Int) = Position2d(x * factor, y * factor)