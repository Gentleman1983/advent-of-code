package de.havox_design.aoc2023.day17

import java.util.*
import kotlin.Comparator

class Day17(private var filename: String) {
    fun solvePart1(): Long =
        parseCoodinateMap()
            .findPath()

    fun solvePart2(): Long =
        94L

    private fun Map<Coordinate, Int>.findPath(
        maxConsecutive: Int = 3, minConsecutive: Int = 0, minMovedForEnd: Int = 0
    ): Long {
        val xRange = xRange()
            .let { (a, b) -> a..b }
        val yRange = yRange()
            .let { (a, b) -> a..b }
        val target = Coordinate(xRange.last, yRange.last)
        val queue = priorityQueueOf(
            Comparator.comparing { -manhattanDistance(it.first.location, target) },
            History(FourDirectionFlipped.RIGHT, origin, 0) to 0L
        )
        val visited = mutableMapOf<History, Long>()
        var min = Long.MAX_VALUE

        while (queue.isNotEmpty()) {
            val (history, loss) = queue.poll()

            when {
                loss > (visited[history] ?: Long.MAX_VALUE) -> continue
            }
            when {
                history.location == target && history.count >= minMovedForEnd -> {
                    min = min.coerceAtMost(loss)
                    continue
                }
            }

            history
                .moveDirections(maxConsecutive, minConsecutive)
                .asSequence()
                .filter { it.location.x in xRange && it.location.y in yRange }
                .map { it to loss + get(it.location)!! }
                .filter { it.second < (visited[it.first] ?: Long.MAX_VALUE) }
                .onEach { visited[it.first] = it.second }
                .toCollection(queue)
        }

        return min

    }

    fun <T> priorityQueueOf(comparator: Comparator<T>, vararg args: T): PriorityQueue<T> {
        val queue = PriorityQueue<T>(comparator)
        queue.addAll(args)

        return queue
    }

    private fun parseCoodinateMap(): Map<Coordinate, Int> =
        getResourceAsText(filename)
            .flatMapIndexed { rowNumber, row ->
                row.mapIndexed { columnNumber, char ->
                    Coordinate(columnNumber, rowNumber) to char.toIntValue()
                }
            }
            .toMap()

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    private fun Char.toIntValue() =
        code - '0'.code
}