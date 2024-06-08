package de.havox_design.aoc2021.day11

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.diagonalNeighbours
import de.havox_design.aoc.utils.kotlin.model.positions.neighbours

class DumboOctopus(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val octopuses = data
            .map { line ->
                line
                    .toCharArray()
                    .map { it.digitToInt() }
                    .toMutableList()
            }

        return (0 until 100)
            .sumOf { runIteration(octopuses) }
    }

    fun processPart2(): Any {
        val octopuses = data
            .map { line ->
                line
                    .toCharArray()
                    .map { it.digitToInt() }
                    .toMutableList()
            }
        var step = 0

        do {
            step += 1

            val flashCount = runIteration(octopuses)
        } while (flashCount != 100)

        return step
    }

    private fun runIteration(octopuses: List<MutableList<Int>>): Int {
        val toFlash = mutableSetOf<Position2d<Int>>()
        val flashed = mutableSetOf<Position2d<Int>>()

        fun increaseEnergy(point: Position2d<Int>) {
            octopuses[point] += 1

            if (octopuses[point] > 9) {
                toFlash.add(point)
            }
        }

        octopuses
            .points()
            .forEach { increaseEnergy(it) }

        while (toFlash.isNotEmpty()) {
            val point = toFlash.first()

            toFlash.remove(point)

            if (flashed.add(point)) {
                (point.neighbours() + point.diagonalNeighbours())
                    .filter { it in octopuses }
                    .forEach { increaseEnergy(it) }
            }
        }

        return flashed
            .onEach { octopuses[it] = 0 }
            .size
    }

    private fun <E> List<List<E>>.points(): ArrayList<Position2d<Int>> =
        indices
            .flatMapTo(ArrayList()) { y ->
                this[y]
                    .indices
                    .map { x -> Position2d(x, y) }
            }

    private operator fun <E> List<List<E>>.get(point: Position2d<Int>) =
        this[point.y][point.x]

    private operator fun <E> List<MutableList<E>>.set(point: Position2d<Int>, value: E) {
        this[point.y][point.x] = value
    }

    private operator fun <E> List<List<E>>.contains(point: Position2d<Int>): Boolean =
        this.isNotEmpty() &&
                point.y in this.indices &&
                point.x in this.first().indices

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
