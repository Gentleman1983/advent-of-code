package de.havox_design.aoc2024.day08

import de.havox_design.aoc2024.day06.IntPair

class ResonantCollinearity(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val height = data.size
    private val width = data.maxOfOrNull { row -> row.length } ?: 0
    private val antennae = buildMap<Char, MutableList<IntPair>> {
        for ((y, row) in data.withIndex()) {
            for ((x, char) in row.withIndex()) {
                if (char != '.') {
                    getOrPut(char) { mutableListOf() }
                        .add(IntPair(x, y))
                }
            }
        }
    }

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        solve(true)

    private fun solve(allMultiples: Boolean = false): Int = buildSet {
        for ((_, points) in antennae) {
            for (positionA in points) {
                for (positionB in points) {
                    if (positionA == positionB) {
                        continue
                    }

                    val dx = positionB.first - positionA.first
                    val dy = positionB.second - positionA.second

                    if (allMultiples) {
                        var i = 0

                        while (addIfInRange(positionA.first + i * dx, positionA.second + i * dy)) i++
                    }
                    else {
                        addIfInRange(positionB.first + dx, positionB.second + dy)
                    }
                }
            }
        }
    }
        .size

    private fun MutableCollection<IntPair>.addIfInRange(x: Int, y: Int): Boolean =
        if (
            x in 0..<width &&
            y in 0..<height
            ) {
            add(IntPair(x, y))
            true
        } else {
            false
        }


    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
