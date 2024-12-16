package de.havox_design.aoc2024.day16

import de.havox_design.aoc.utils.kotlin.model.chargrid.CharGrid
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import java.util.*

class ReindeerMaze(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val grid = CharGrid.fromString(data)
        val start = grid.findPoint('S') ?: error("Start point not found")
        val end = grid.findPoint('E') ?: error("End point not found")

        return findLowestScore(grid, start, end).first
    }

    fun processPart2(): Any =
        0L

    private fun CharGrid.findPoint(target: Char): Position2d<Int>? =
        this
            .firstOrNull { it.value == target }
            ?.point

    @SuppressWarnings("kotlin:S3776")
    private fun findLowestScore(
        grid: CharGrid,
        start: Position2d<Int>,
        end: Position2d<Int>,
        findAllPaths: Boolean = false
    ): Pair<Int, Set<Position2d<Int>>> {
        val visited = mutableMapOf<Pair<Position2d<Int>, MazeDirection>, Int>()
        val queue = PriorityQueue<State>(compareBy { it.score })

        queue
            .add(
                State(
                    start,
                    MazeDirection.RIGHT,
                    0,
                    if (findAllPaths) {
                        setOf(start)
                    } else {
                        emptySet()
                    }
                )
            )

        var bestScore = Int.MAX_VALUE
        val bestPathTiles = mutableSetOf<Position2d<Int>>()

        while (queue.isNotEmpty()) {
            val (pos, dir, score, path) = queue
                .poll()

            if (pos == end) {
                if (score <= bestScore) {
                    if (score < bestScore) {
                        bestScore = score

                        if (findAllPaths) {
                            bestPathTiles.clear()
                        }
                    }

                    if (findAllPaths) {
                        bestPathTiles.addAll(path)
                    }
                }

                if (!findAllPaths) {
                    break
                }

                continue
            }

            if (score > bestScore) {
                continue
            }

            val stateKey = Pair(pos, dir)

            if (visited[stateKey]?.let { it < score } == true) {
                continue
            }

            visited[stateKey] = score

            queue
                .add(State(pos, dir.rotateRight(), score + 1000, path))
            queue
                .add(State(pos, dir.rotateLeft(), score + 1000, path))

            val forward = pos + dir

            if (grid.isValidPosition(forward) && grid[forward] != '#') {
                queue
                    .add(
                        State(
                            forward,
                            dir,
                            score + 1,
                            if (findAllPaths) {
                                path + forward
                            } else {
                                emptySet()
                            }
                        )
                    )
            }
        }

        return bestScore to bestPathTiles
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
