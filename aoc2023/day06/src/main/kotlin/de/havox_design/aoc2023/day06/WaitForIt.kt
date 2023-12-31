package de.havox_design.aoc2023.day06

import kotlin.text.StringBuilder

class WaitForIt(private var filename: String) {
    private val DISTANCE_INDEX = 1
    private val DISTANCE_PREFIX = "Distance: "
    private val REGEX_SPACE = "\\s+".toRegex()
    private val TIME_INDEX = 0
    private val TIME_PREFIX = "Time: "

    fun solvePart1(): Long {
        val combinations = convertInput(getResourceAsText(filename))
            .map { game -> simulate(game.first, game.second) }
        var product = 1L

        for(value in combinations) {
            product *= value
        }

        return product
    }

    fun solvePart2(): Long {
        val games = convertInput(getResourceAsText(filename))
        val game = fixInput(games)

        return simulate(game.first, game.second).toLong()
    }

    private fun fixInput(input: List<Pair<Long, Long>>): Pair<Long, Long> {
        var duration = StringBuilder()
        var distance = StringBuilder()

        for(entry in input) {
            duration.append(entry.first)
            distance.append(entry.second)
        }

        return Pair(duration.toString().toLong(), distance.toString().toLong())
    }

    private fun convertInput(input: List<String>): List<Pair<Long, Long>> {
        val times = input[TIME_INDEX]
            .substringAfter(TIME_PREFIX)
            .trim()
            .split(REGEX_SPACE)
            .map { it.toLong() }
        val distances = input[DISTANCE_INDEX]
            .substringAfter(DISTANCE_PREFIX)
            .trim()
            .split(REGEX_SPACE)
            .map { it.toLong() }
        val result = ArrayList<Pair<Long, Long>>()

        for(index in times.indices) {
            result.add(Pair(times[index], distances[index]))
        }

        return result
    }

    private fun simulate(time: Long, goal: Long): Int {
        var count = 0

        for (i in 0..< time) {
            if ((time - i) * i > goal) {
                count++
            }
        }

        return count
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}