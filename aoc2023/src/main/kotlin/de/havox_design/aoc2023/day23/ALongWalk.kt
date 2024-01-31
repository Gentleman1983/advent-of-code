package de.havox_design.aoc2023.day23

import de.havox_design.aoc.utils.kotlin.model.coordinates.*
import java.util.*

class ALongWalk(private var filename: String) {
    private val ICON_FORREST = '#'
    private val ICON_PATH = '.'
    private val ICON_SLOPE_DOWN = 'v'
    private val ICON_SLOPE_LEFT = '<'
    private val ICON_SLOPE_RIGHT = '>'
    private val ICON_SLOPE_UP = '^'


    fun solvePart1(): Long =
        findLongestWay(parseCoordinateMap())

    fun solvePart2(): Int =
        findLongestWayIgnoringSlopes(parseCoordinateMap())

    @SuppressWarnings("kotlin:S6611")
    private fun findLongestWay(map: Map<Coordinate, Char>): Long {
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

            FourDirectionsFlipped.entries
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

    @SuppressWarnings("kotlin:S3776", "kotlin:S6611")
    private fun findLongestWayIgnoringSlopes(map: Map<Coordinate, Char>): Int {
        val xRange = map
            .xRange()
            .let { (a, b) -> a..b }
        val yRange = map
            .yRange()
            .let { (a, b) -> a..b }
        val adjacencyMap = map
            .filter { it.value != ICON_FORREST }
            .keys
            .associateWith { coordinate ->
                adjacentCoordinates(coordinate)
                    .filter { it.x in xRange && it.y in yRange }
                    .filter { map[it]!! != ICON_FORREST }
                    .toList()
            }
            .toMutableMap()
        val distanceMap = adjacencyMap
            .flatMap { (key, value) ->
                value.map { key to it } + value.map { it to key }
            }
            .associateWith { 1 }
            .toMutableMap()
        val canBeFixed = adjacencyMap
            .filter { it.value.size == 2 }
        val fixQueue = canBeFixed
            .keys
            .toMutableList()
        while (fixQueue.isNotEmpty()) {
            val current = fixQueue.removeFirst()

            when (current) {
                !in adjacencyMap -> continue
            }

            var (from, to) = adjacencyMap[current]!!
            var diff = 2
            var lastFrom = current
            var next: Coordinate

            while (adjacencyMap[from]!!.size == 2) {
                next = adjacencyMap[from]!!
                    .first { it != lastFrom }
                adjacencyMap.remove(from)
                lastFrom = from
                from = next
                diff++
            }

            var lastTo = current

            while (adjacencyMap[to]!!.size == 2) {
                next = adjacencyMap[to]!!
                    .first { it != lastTo }
                adjacencyMap.remove(to)
                lastTo = to
                to = next
                diff++
            }

            adjacencyMap.remove(current)
            adjacencyMap[from] = adjacencyMap[from]!!
                .mapNotNull {
                    when (it) {
                        from -> null
                        lastFrom -> to
                        else -> it
                    }
                }

            adjacencyMap[to] = adjacencyMap[to]!!
                .mapNotNull {
                    when (it) {
                        to -> null
                        lastTo -> from
                        else -> it
                    }
                }

            distanceMap[from to to] = diff
            distanceMap[to to from] = diff
        }

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
        val queue = priorityQueueOf(Comparator
            .comparing { it: Triple<Coordinate, Int, Set<Coordinate>> ->
                -it.second
            }
        )
        var count = 0L
        val visited = mutableMapOf<Pair<Coordinate, Int>, Int>()
        var longestTrail = 0

        queue.add(Triple(start, 0, setOf(start)))

        while (queue.isNotEmpty()) {
            val (current, length, path) = queue.poll()

            when (current) {
                target -> {
                    longestTrail = longestTrail
                        .coerceAtLeast(length)
                }
            }

            count++
            visited[current to path.size] = length
            adjacencyMap[current]!!
                .asSequence()
                .filter { it !in path }
                .map { Triple(it, length + distanceMap[current to it]!!, path + it) }
                .toCollection(queue)
        }

        return longestTrail
    }

    private fun FourDirectionsFlipped.uphill() =
        when (this) {
            FourDirectionsFlipped.DOWN -> ICON_SLOPE_UP
            FourDirectionsFlipped.LEFT -> ICON_SLOPE_RIGHT
            FourDirectionsFlipped.RIGHT -> ICON_SLOPE_LEFT
            FourDirectionsFlipped.UP -> ICON_SLOPE_DOWN
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