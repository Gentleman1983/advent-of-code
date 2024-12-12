package de.havox_design.aoc2024.day12

import de.havox_design.aoc.utils.kotlin.model.field.Field
import de.havox_design.aoc.utils.kotlin.model.field.toField
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours

class GardenGroups(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any =
        calculatePrice()

    fun processPart2(): Any =
        0L

    private fun calculatePrice(): Int {
        val flowersToCheck = data
            .allPositions
            .toMutableSet()
        var totalPrice = 0

        while (flowersToCheck.isNotEmpty()) {
            val position = flowersToCheck
                .first()
            val flower = data[position]

            val (area, fullPerimeter, seen) = calculateAreaAndPerimeter(position, flower)
            flowersToCheck
                .removeAll(seen)

            totalPrice += area * fullPerimeter
        }

        return totalPrice
    }

    private fun calculateAreaAndPerimeter(
        start: Position2d<Int>,
        flower: String
    ): Triple<Int, Int, Set<Position2d<Int>>> {
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