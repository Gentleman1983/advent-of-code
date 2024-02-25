package de.havox_design.aoc2018.day23

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d
import java.util.regex.Pattern
import kotlin.math.abs

class ExperimentalEmergencyTeleportationPartTwo(private var filename: String) {
    private val nanobotPattern = "pos=<(?<x>-?\\d+),(?<y>-?\\d+),(?<z>-?\\d+)>,\\s+r=(?<r>-?\\d+)"
    fun processTask2(): Any =
        process()

    private fun process(): Int {
        val nanobots = parseNanobots()
        var range = maxOf(
            nanobots.deltaBy { point.x },
            nanobots.deltaBy { point.y },
            nanobots.deltaBy { point.z }
        )
        val origin = Position3d(0, 0, 0)
        var candidates = setOf(NanobotPartTwo(origin, range))

        while (range > 0) {
            range = (range / 2) + if (range > 2) 1 else 0
            candidates = candidates.flatMap { bot ->
                bot
                    .point
                    .adjacent(range)
                    .map { point -> NanobotPartTwo(point, range) }
            }
                .groupBy { bot -> nanobots.count { bot.inTotalRangeTo(it) } }
                .maxBy { it.key }.value.toSet()
        }

        return candidates
            .minOfOrNull { it.point.manhattanDistance(origin) }!!
    }

    private fun parseNanobots() =
        getResourceAsText(filename)
            .map { line ->
                val pattern = Pattern.compile(nanobotPattern)
                val matcher = pattern.matcher(line)

                if (matcher.matches()) {
                    return@map NanobotPartTwo.from(
                        x = matcher.group("x"),
                        y = matcher.group("y"),
                        z = matcher.group("z"),
                        r = matcher.group("r")
                    )
                } else {
                    throw IllegalArgumentException("Could not parse '$line'. It does not match regex '$nanobotPattern'.")
                }
            }
            .toList()

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}

fun Position3d<Int>.manhattanDistance(other: Position3d<Int>) =
    listOf(x - other.x, y - other.y, z - other.z)
        .sumOf { abs(it) }

private fun Position3d<Int>.adjacent(offset: Int) =
    (-1..1).flatMap { a ->
        (-1..1).flatMap { b ->
            (-1..1).map { c ->
                Position3d(x + a * offset, y + b * offset, z + c * offset)
            }
        }
    }

private inline fun <T> List<T>.deltaBy(block: T.() -> Int) =
    with(map(block)) { abs((max()) - (min())) }
