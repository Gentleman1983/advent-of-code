package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped

private const val DEFINITION = "(.)=(\\d+), .=(\\d+)..(\\d+)"

class ReservoirResearch(private var filename: String) {
    private val source = Coordinate(500, 0)
    fun processTask1(): Any {
        val state = process(getResourceAsText(filename))
        val yMin = state.getTokens().minBy { it.y() }.y()
        return state.flatMap { it.additions }
            .distinctBy { it.point }
            .count { it.y() >= yMin }
    }

    fun processTask2(): Any =
        process(getResourceAsText(filename))
            .flatMap { it.additions }
            .count { it is StillWater }

    private fun process(input: List<String>): Ground {
        val veins = input
            .parseWith(DEFINITION) { (axis, a, b1, b2) ->
                when (axis) {
                    "x" -> (b1.toInt()..b2.toInt()).map { b -> Clay(Coordinate(a.toInt(), b)) }
                    else -> (b1.toInt()..b2.toInt()).map { b -> Clay(Coordinate(b, a.toInt())) }
                }
            }
            .flatten()
        val boundary: Boundary = getBoundary(
            setOf(source)
                .union(veins.map { it.point })
        )
            .let { (topLeft, bottomRight) -> topLeft.west() to bottomRight.east() }
        val base = "."
            .repeat(boundary.getWidth())

        return (1..boundary.getHeight()).map { y ->
            Layer(
                veins
                    .filter { it.atRow(y - 1) }
                    .toSet(),
                base
            )
        }
            .also { it.flow(boundary, source) }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    private fun Coordinate.getNeighbor(direction: FourDirectionsFlipped) =
        Coordinate(this.x + direction.dx(), this.y + direction.dy())

    private fun Coordinate.east() =
        getNeighbor(FourDirectionsFlipped.RIGHT)

    private fun Coordinate.west() =
        getNeighbor(FourDirectionsFlipped.LEFT)

    private inline fun <T> List<CharSequence>.parseWith(pattern: String, mapper: (MatchResult.Destructured) -> T) =
        map { pattern.parseFor(it, mapper) }

    private inline fun <T> String.parseFor(input: CharSequence, mapper: (MatchResult.Destructured) -> T) =
        (toRegex().matchEntire(input) ?: throw IllegalArgumentException("Wrong format.")).destructured.let(mapper)
}