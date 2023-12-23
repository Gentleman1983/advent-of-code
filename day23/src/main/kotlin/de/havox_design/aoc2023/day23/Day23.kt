package de.havox_design.aoc2023.day23

import de.havox_design.aoc2023.day17.Coordinate
import de.havox_design.aoc2023.day17.FourDirectionFlipped
import de.havox_design.aoc2023.day17.xRange
import de.havox_design.aoc2023.day17.yRange
import java.util.*
import kotlin.Comparator

class Day23(private var filename: String) {
    private val ICON_FORREST = '#'
    private val ICON_PATH = '.'
    private val ICON_SLOPE_DOWN = 'v'
    private val ICON_SLOPE_LEFT = '<'
    private val ICON_SLOPE_RIGHT = '>'
    private val ICON_SLOPE_UP = '^'

    fun solvePart1(): Long {
        val map = parseCoordinateMap()
        val xRange = map.xRange()
            .let { (a, b) -> a..b }
        val yRange = map.yRange()
            .let { (a, b) -> a..b }
        val queue = priorityQueueOf(
            Comparator
                .comparing { it: Triple<Coordinate, Long, Set<Coordinate>> ->
                    it.second
                }
        )
        val start = map
            .filter { it.key.y == yRange.first }
            .filter { it.value == ICON_PATH }
            .keys
            .first()
        val target = map
            .filter { it.key.y == yRange.last }
            .filter { it.value == ICON_PATH }
            .keys
            .first()
        val visited = mutableMapOf<Coordinate, Long>()
        var longestTrail = 0L

        queue.add(Triple(start, 0L, setOf(start)))
        while (queue.isNotEmpty()) {
            val (current, length, path) = queue.poll()

            when (current) {
                target -> {
                    longestTrail = longestTrail
                        .coerceAtLeast(length)
                }
            }

            when {
                current in visited && visited[current]!! > length -> continue
            }

            visited[current] = length

            FourDirectionFlipped.entries
                .asSequence()
                .map { it to it + current }
                .filter { (_, coordinate) -> coordinate.x in xRange && coordinate.y in yRange }
                .filter { (_, coordinate) -> coordinate !in path }
                .filter { (direction, coordinate) ->
                    map[coordinate]!! != ICON_FORREST
                            && map[coordinate]!! != direction.uphill()
                }
                .map { (_, it) -> Triple(it, length + 1, path + it) }
                .toCollection(queue)
        }
        return longestTrail
    }

    fun solvePart2(): Long =
        0L

    private fun FourDirectionFlipped.uphill() =
        when (this) {
            FourDirectionFlipped.DOWN -> ICON_SLOPE_UP
            FourDirectionFlipped.LEFT -> ICON_SLOPE_RIGHT
            FourDirectionFlipped.RIGHT -> ICON_SLOPE_LEFT
            FourDirectionFlipped.UP -> ICON_SLOPE_DOWN
        }

    private fun <T> priorityQueueOf(comparator: java.util.Comparator<T>, vararg args: T): PriorityQueue<T> {
        val queue = PriorityQueue<T>(comparator)
        queue.addAll(args)

        return queue
    }

    private fun parseCoordinateMap(): Map<Coordinate, Char> =
        getResourceAsText(filename)
            .flatMapIndexed { rowNumber, row ->
                row
                    .mapIndexed { columnNumber, char ->
                        Coordinate(columnNumber, rowNumber) to char
                    }
            }
            .toMap()

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}