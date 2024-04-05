package de.havox_design.aoc2019.day18

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc.utils.kotlin.model.coordinates.adjacentCoordinates

class Maze(
    private val starts: Set<Coordinate>,
    private val keys: Map<Coordinate, Char>,
    private val doors: Map<Coordinate, Char>,
    private val openSpaces: Set<Coordinate>
) {
    private fun findReachableKeys(
        from: Coordinate,
        haveKeys: Set<Char> = mutableSetOf()
    ): Map<Char, Pair<Coordinate, Int>> {
        val queue = ArrayDeque<Coordinate>().apply { add(from) }
        val distance = mutableMapOf(from to 0)
        val keyDistance = mutableMapOf<Char, Pair<Coordinate, Int>>()

        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()

            adjacentCoordinates(next)
                .filter { it in openSpaces }
                .filterNot { it in distance }
                .forEach { point: Coordinate ->
                    distance[point] = distance[next]!! + 1

                    val door = doors[point]
                    val key = keys[point]

                    if (door == null || door.lowercaseChar() in haveKeys) {
                        if (key != null && key !in haveKeys) {
                            keyDistance[key] = point to distance[point]!!
                        } else {
                            queue.add(point)
                        }
                    }
                }
        }

        return keyDistance
    }

    private fun findReachableFromPoints(
        from: Set<Coordinate>,
        haveKeys: Set<Char>
    ): Map<Char, Triple<Coordinate, Int, Coordinate>> =
        from
            .map { point ->
                findReachableKeys(point, haveKeys)
                    .map { entry ->
                        entry.key to Triple(entry.value.first, entry.value.second, point)
                    }
            }
            .flatten()
            .toMap()

    fun minimumSteps(
        from: Set<Coordinate> = starts,
        haveKeys: Set<Char> = mutableSetOf(),
        seen: MutableMap<Pair<Set<Coordinate>, Set<Char>>, Int> = mutableMapOf()
    ): Int {
        val state = Pair(from, haveKeys)

        if (state in seen) {
            return seen.getValue(state)
        }

        val answer = findReachableFromPoints(from, haveKeys)
            .map { entry ->
                val (at, dist, cause) = entry.value

                dist + minimumSteps((from - cause) + at, haveKeys + entry.key, seen)
            }
            .minOrNull() ?: 0

        seen[state] = answer

        return answer
    }

    companion object {
        fun from(input: List<String>): Maze {
            val starts = mutableSetOf<Coordinate>()
            val keys = mutableMapOf<Coordinate, Char>()
            val doors = mutableMapOf<Coordinate, Char>()
            val openSpaces = mutableSetOf<Coordinate>()

            input
                .forEachIndexed { y, row ->
                    row
                        .forEachIndexed { x, c ->
                            val place = Coordinate(x, y)

                            if (c == '@') {
                                starts += place
                            }

                            if (c != '#') {
                                openSpaces += place
                            }

                            if (c in ('a'..'z')) {
                                keys[place] = c
                            }

                            if (c in ('A'..'Z')) {
                                doors[place] = c
                            }
                        }
                }

            return Maze(starts, keys, doors, openSpaces)
        }
    }
}
