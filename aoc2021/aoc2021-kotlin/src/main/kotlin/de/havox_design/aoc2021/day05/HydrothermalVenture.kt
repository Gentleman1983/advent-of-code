package de.havox_design.aoc2021.day05

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class HydrothermalVenture(private var filename: String) {
    private val DELIMITER_COORDINATED = ","
    private val DELIMITER_POINTS = " -> "

    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        0L

    private fun solve() =
        data
            .map { line ->
                line
                    .split(DELIMITER_POINTS, DELIMITER_COORDINATED)
                    .map { it.toInt() }
            }
            .map { (a, b, c, d) -> Position2d(a, b) to Position2d(c, d) }
            .flatMap { (pointA, pointB) -> pointsBetween(pointA, pointB) }
            .groupingBy { it }
            .eachCount()
            .count { (_, value) -> value > 1 }

    private fun pointsBetween(pointA: Position2d<Int>, pointB: Position2d<Int>): List<Position2d<Int>> {
        return if (pointA.x == pointB.x) {
            yRange(pointA, pointB).map { y -> Position2d(pointA.x, y) }
        } else if (pointA.y == pointB.y) {
            xRange(pointA, pointB).map { x -> Position2d(x, pointA.y) }
        } else {
            emptyList()
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
