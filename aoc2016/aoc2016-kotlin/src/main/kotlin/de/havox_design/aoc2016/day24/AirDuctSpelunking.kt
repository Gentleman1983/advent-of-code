package de.havox_design.aoc2016.day24

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import java.util.ArrayDeque

class AirDuctSpelunking(private var filename: String) {
    fun solvePart1(): Int =
        shortestPath(parseInput(), false)

    fun solvePart2(): Int =
        shortestPath(parseInput(), true)

    private fun shortestPath(map: List<List<Position>>, isReturning: Boolean): Int {
        val (targets, distances) = mapDistances(map)
            .let {
                it
                    .keys
                    .flatMap { (a, b) -> listOf(a, b) }
                    .toSet() to it
            }

        fun move(leftover: Set<Int>, current: Int, steps: Int): Int =
            if (leftover.isEmpty()) {
                steps + (distances.getValue(current to 0).takeIf { isReturning } ?: 0)
            } else {
                targets
                    .filter { it in leftover }
                    .minOf { move(leftover - it, it, steps + distances.getValue(current to it)) }
            }

        return move(targets - 0, 0, 0)
    }

    private fun mapDistances(map: List<List<Position>>): Map<Pair<Int, Int>, Int> {
        val targets = map
            .flatMap { r -> r.mapNotNull { p -> p.n?.let { it to p } } }
            .toMap()

        return targets
            .flatMap { (target, position) ->
                targets
                    .filter { it.key > target }
                    .flatMap { (otherTarget, otherPosition) ->
                        val distance = position.from(otherPosition, map)
                        listOf(target to otherTarget, otherTarget to target).map { it to distance }
                    }
            }
            .toMap()
    }

    private fun Position.from(other: Position, board: List<List<Position>>): Int {
        val seen = mutableMapOf(this to 0)
        val queue = ArrayDeque<Position>().also { it.add(this) }

        while (queue.isNotEmpty()) {
            val current = queue.pop()
            val distance = seen.getValue(current)

            if (current == other) {
                return distance
            }

            listOf(
                board[current.position.y][current.position.x - 1],
                board[current.position.y][current.position.x + 1],
                board[current.position.y - 1][current.position.x],
                board[current.position.y + 1][current.position.x]
            )
                .filter { it.isValid && !it.isWall && it !in seen }
                .let { neighbors ->
                    seen.putAll(neighbors.map { it to distance + 1 })
                    queue.addAll(neighbors)
                }
        }
        return 0
    }

    private fun parseInput(): List<List<Position>> {
        val input = getResourceAsText(filename)

        return input
            .indices
            .map { y ->
                input[0]
                    .indices
                    .map { x -> Position(Position2d(x, y), input[y][x].toString()) }
            }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
