package de.havox_design.aoc2022.day18

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d

class BoilingBoulders(private var filename: String) {
    private val data = readFile()

    fun processPart1(): Int =
        data
            .sumOf { point ->
                point
                    .neighbours()
                    .count { it !in data }
            }

    fun processPart2(): Int {
        val xLimits = data.minOf { it.x } - 1..data.maxOf { it.x } + 1
        val yLimits = data.minOf { it.y } - 1..data.maxOf { it.y } + 1
        val zLimits = data.minOf { it.z } - 1..data.maxOf { it.z } + 1

        val toVisit = mutableListOf(Position3d(xLimits.first, yLimits.first, zLimits.first))
        val visited = mutableSetOf<Position3d<Int>>()

        var sides = 0
        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()
            if (current !in visited) {
                current.neighbours()
                    .filter { it.x in xLimits && it.y in yLimits && it.z in zLimits }
                    .forEach { next -> if (next in data) sides++ else toVisit += next }
                visited += current
            }
        }
        return sides
    }

    private fun readFile(): Set<Position3d<Int>> {
        val fileData = getResourceAsText(filename)

        return fileData
            .map { row ->
                row
                    .split(",")
                    .map(String::toInt)
            }
            .map { (x, y, z) -> Position3d(x, y, z) }
            .toSet()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}

private fun Position3d<Int>.neighbours(): List<Position3d<Int>> {
    return listOf(
        Position3d<Int>(x - 1, y, z),
        Position3d<Int>(x + 1, y, z),
        Position3d<Int>(x, y - 1, z),
        Position3d<Int>(x, y + 1, z),
        Position3d<Int>(x, y, z - 1),
        Position3d<Int>(x, y, z + 1)
    )
}
