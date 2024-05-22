package de.havox_design.aoc2021.day05

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class HydrothermalVenture(private var filename: String) {
    private val DELIMITER_COORDINATED = ","
    private val DELIMITER_POINTS = " -> "

    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        solve(true)

    private fun solve(withDiagonals: Boolean=false) =
        data
            .map { line ->
                line
                    .split(DELIMITER_POINTS, DELIMITER_COORDINATED)
                    .map { it.toInt() }
            }
            .map { (a, b, c, d) -> Position2d(a, b) to Position2d(c, d) }
            .flatMap { (pointA, pointB) -> pointsBetween(pointA, pointB, withDiagonals) }
            .groupingBy { it }
            .eachCount()
            .count { (_, value) -> value > 1 }

    private fun pointsBetween(pointA: Position2d<Int>, pointB: Position2d<Int>, withDiagonals: Boolean):
            List<Position2d<Int>> {
        return when {
            pointA.x == pointB.x -> {
                yRange(pointA, pointB)
                    .map { y -> Position2d(pointA.x, y) }
            }
            pointA.y == pointB.y -> {
                xRange(pointA, pointB)
                    .map { x -> Position2d(x, pointA.y) }
            }
            withDiagonals -> {
                val yRange = yRange(pointA, pointB)
                val xRange = xRange(pointA, pointB)

                xRange
                    .zip(yRange)
                    .map { (x, y) -> Position2d(x, y) }
            }
            else -> {
                emptyList()
            }
        }
    }

    private fun xRange(pointA: Position2d<Int>, pointB: Position2d<Int>): IntProgression =
        makeRange(pointA.x, pointB.x)

    private fun yRange(pointA: Position2d<Int>, pointB: Position2d<Int>): IntProgression =
        makeRange(pointA.y, pointB.y)

    private fun makeRange(a: Int, b: Int) =
        if (a < b) {
            a..b
        } else {
            a downTo b
        }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
