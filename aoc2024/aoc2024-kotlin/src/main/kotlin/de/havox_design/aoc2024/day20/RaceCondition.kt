package de.havox_design.aoc2024.day20

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.distance
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours
import java.util.*

class RaceCondition(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val maze = parseMaze(data)

    fun processPart1(minimumCostSaving: Int = 100): Any =
        solve(maze, minimumCostSaving, CHEAT_LENGTH_PART_A)

    fun processPart2(minimumCostSaving: Int = 100): Any =
        solve(maze, minimumCostSaving, CHEAT_LENGTH_PART_B)

    private fun solve(maze: Maze, minimumCostSaving: Int, cheatLength: Int): Int {
        val pathResult = shortestPath(
            start = maze.start,
            end = { it == maze.end },
            neighbours = { it.neighbours().filter { p -> p !in maze.walls } }
        )
        val path = pathResult.getVertices()

        return path.withIndex().sumOf { (index, cheatStart) ->
            path.drop(index + 1).count { cheatEnd ->
                val distance = cheatStart.distance(cheatEnd)
                distance <= cheatLength && pathResult.getCost(cheatEnd) - distance - index >= minimumCostSaving
            }
        }
    }

    private fun <T> String.toMapGrid(transform: (Position2d<Int>, Char) -> T?): MapGrid<T> {
        val grid = mutableMapOf<Position2d<Int>, T>()

        lines()
            .forEachIndexed { y: Int, line: String ->
                line
                    .forEachIndexed { x, char ->
                        val point = Position2d(x, y)

                        transform(point, char)
                            ?.let { grid[point] = it }
                    }
            }

        return grid
    }

    private fun <T> shortestPath(
        start: T,
        end: (T) -> Boolean,
        neighbours: (T) -> Iterable<T>,
        cost: (T, T) -> Int = { _, _ -> 1 },
        heuristic: (T) -> Int = { 0 }
    ): PathResult<T> =
        shortestPath(listOf(ScoredVertex(start, 0, heuristic(start))), end, neighbours, cost, heuristic)

    @SuppressWarnings("kotlin:S6611")
    private fun <T> shortestPath(
        initialToVisit: List<ScoredVertex<T>>,
        end: (T) -> Boolean,
        neighbours: (T) -> Iterable<T>,
        cost: (T, T) -> Int,
        heuristic: (T) -> Int = { 0 },
    ): PathResult<T> {
        val toVisit = PriorityQueue(initialToVisit)
        val seenPoints: MutableMap<T, SeenVertex<T>> = initialToVisit
            .associateTo(mutableMapOf()) { (vertex, score) ->
                vertex to SeenVertex(score, null)
            }
        val possiblePaths = mutableMapOf<T, MutableSet<T>>()
        var endVertex: T? = null

        while (endVertex == null && toVisit.isNotEmpty()) {
            val (currentVertex, currentCost) = toVisit
                .remove()

            endVertex = if (end(currentVertex)) {
                currentVertex
            } else {
                null
            }

            neighbours(currentVertex)
                .forEach { next ->
                    val nextCost = currentCost + cost(currentVertex, next)
                    val heuristicCost = heuristic(next)
                    val bestCost = seenPoints[next]
                        ?.cost ?: Int.MAX_VALUE

                    if (nextCost < bestCost) {
                        possiblePaths[next] = mutableSetOf(currentVertex)
                        seenPoints[next] = SeenVertex(nextCost, currentVertex)
                        toVisit
                            .add(ScoredVertex(next, nextCost, heuristicCost))
                    } else if (nextCost == bestCost) {
                        possiblePaths[next]!!
                            .add(currentVertex)
                    }
                }
        }

        val starts = initialToVisit
            .mapTo(mutableSetOf()) { it.vertex }

        return PathResult(starts, endVertex, seenPoints, possiblePaths)
    }

    private fun parseMaze(input: String): Maze {
        var start = Position2d(0, 0)
        var end = Position2d(0, 0)
        val walls = mutableSetOf<Position2d<Int>>()

        input
            .toMapGrid { point, char ->
                when (char) {
                    ICON_WALL -> walls += point
                    ICON_START -> start = point
                    ICON_END -> end = point
                }
            }

        return Maze(walls, start, end)
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val CHEAT_LENGTH_PART_A = 2
        private const val CHEAT_LENGTH_PART_B = 20

        private const val ICON_END = 'E'
        private const val ICON_START = 'S'
        private const val ICON_WALL = '#'
    }
}

private typealias MapGrid<T> =
        MutableMap<Position2d<Int>, T>
