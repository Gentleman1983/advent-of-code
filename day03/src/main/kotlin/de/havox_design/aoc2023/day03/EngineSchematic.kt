package de.havox_design.aoc2023.day03

import java.lang.IllegalArgumentException

class EngineSchematic(input: List<String>) {
    private val schematic = ArrayList<List<Point>>()
    private val partNumbers = HashSet<PartNumber>()
    private val gears = HashSet<Pair<Point, Long>>()

    init {
        buildSchematics(input)
        collectPartNumbers()
        collectGears()
    }

    fun sumOfPartNumbers(): Long =
        partNumbers
            .sumOf { partNumber -> partNumber.getValue() }

    fun sumOfGearRatios(): Long =
        gears
            .sumOf { gear -> gear.second }

    private fun buildSchematics(input: List<String>) {
        for (y: Int in input.indices) {
            val dataRow = input[y]
            val row = ArrayList<Point>()

            for (x: Int in dataRow.indices) {
                val symbol = dataRow[x]

                row.add(Point(row = y, col = x, value = symbol))
            }

            schematic.add(row)
        }
    }

    private fun collectPartNumbers() {
        for (y: Int in schematic.indices) {
            val row = schematic[y]

            for (x: Int in row.indices) {
                val currentElement = row[x]

                if (currentElement.isNumber()
                    && ((isOnGrid(currentElement.getLeftNeighborCoordinate())
                            && !getPosition(currentElement.getLeftNeighborCoordinate()).isNumber())
                            || !isOnGrid(currentElement.getLeftNeighborCoordinate()))
                ) {
                    val potentialPartNumber = detectPartNumber(currentElement)

                    if (potentialPartNumber != null) {
                        partNumbers.add(potentialPartNumber)
                    }
                }
            }
        }
    }

    @SuppressWarnings("kotlin:S3776")
    private fun collectGears() {
        for (y: Int in schematic.indices) {
            val row = schematic[y]

            for (x: Int in row.indices) {
                val currentElement = row[x]

                if (currentElement.isGearSymbol()) {
                    val neighboringPartNumbers = currentElement
                        .getNeighboringCoordinates()
                        .filter { coordinate -> isOnGrid(coordinate) }
                        .map { coordinate -> getPosition(coordinate) }
                        .filter { point -> point.isNumber() }
                        .toSet()

                    val parts = HashSet<PartNumber>()
                    for (point: Point in neighboringPartNumbers) {
                        partNumbers
                            .filter { partNumber -> partNumber.containsPoint(point) }
                            .forEach { partNumber -> parts.add(partNumber) }
                    }

                    if (parts.size > 1) {
                        var gearRatio = 1L
                        for (partNumber: PartNumber in parts) {
                            gearRatio *= partNumber.getValue()
                        }

                        gears.add(Pair(currentElement, gearRatio))
                    }
                }
            }
        }
    }

    private fun detectPartNumber(point: Point): PartNumber? {
        val partNumber = ArrayList<Point>()
        partNumber.add(point)
        var currentPoint = point

        do {
            if (
                isOnGrid(currentPoint.getRightNeighborCoordinate())
                && getPosition(currentPoint.getRightNeighborCoordinate()).isNumber()
            ) {
                currentPoint = getPosition(currentPoint.getRightNeighborCoordinate())
                partNumber.add(currentPoint)
            } else {
                break
            }
        } while (true)

        var neighboringSymbol = false
        for (current: Point in partNumber) {
            neighboringSymbol = neighboringSymbol || current
                .getNeighboringCoordinates()
                .filter { position -> isOnGrid(position) }
                .map { position -> getPosition(position) }
                .any { p -> p.isSymbol() }
        }

        if (neighboringSymbol) {
            return PartNumber(partNumber)
        }

        return null
    }

    private fun isOnGrid(position: Pair<Int, Int>): Boolean =
        position.first >= 0
                && position.first < schematic.size
                && position.second >= 0
                && position.second < schematic[0].size

    private fun getPosition(position: Pair<Int, Int>): Point {
        if (isOnGrid(position)) {
            return schematic[position.first][position.second]
        }
        throw IllegalArgumentException("Position '$position' not on schematic.")
    }
}