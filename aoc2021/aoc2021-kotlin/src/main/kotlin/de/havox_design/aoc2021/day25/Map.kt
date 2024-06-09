package de.havox_design.aoc2021.day25

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class Map(val chars: List<List<Char>>) : List<List<Char>> by chars {

    private fun normalizePoint(point: Position2d<Int>): Position2d<Int> {
        return if (point in chars) {
            point
        } else {
            val x = (point.x + chars.first().size) % chars.first().size
            val y = (point.y + chars.size) % chars.size

            Position2d(x, y)
        }
    }

    fun moveEast(): Pair<Int, Map> {
        val newMap = chars
            .map { it.toMutableList() }
        val points = chars
            .points()
            .map { it to normalizePoint(Position2d(it.x + 1, it.y)) }
            .filter { (origin, target) ->
                chars[origin] == '>' && chars[target] == '.'
            }
            .onEach { (origin, target) ->
                newMap[origin] = '.'
                newMap[target] = '>'
            }

        return points.size to Map(newMap)
    }

    fun moveSouth(): Pair<Int, Map> {
        val newMap = chars
            .map { it.toMutableList() }
        val points = chars
            .points()
            .map { it to normalizePoint(Position2d(it.x, it.y + 1)) }
            .filter { (origin, target) ->
                chars[origin] == 'v' && chars[target] == '.'
            }
            .onEach { (origin, target) ->
                newMap[origin] = '.'
                newMap[target] = 'v'
            }

        return points.size to Map(newMap)
    }

    private fun <E> List<List<E>>.points(): ArrayList<Position2d<Int>> {
        return indices
            .flatMapTo(ArrayList()) { y ->
                this[y]
                    .indices
                    .map { x -> Position2d(x, y) }
            }
    }

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private operator fun <E> List<MutableList<E>>.set(point: Position2d<Int>, value: E) {
        this[point.y][point.x] = value
    }
}
