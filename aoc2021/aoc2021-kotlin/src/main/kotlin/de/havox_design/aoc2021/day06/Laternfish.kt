package de.havox_design.aoc2021.day06

import java.util.*


class Laternfish(private var filename: String) {
    private val FISH_DELIMITER = ","
    private val FISH_TIMER_NEW = 8
    private val FISH_TIMER_RESET = 6
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        0L

    private fun solve(days: Int = 80): Int {
        var day = 0
        val fishes = parseData().toMutableList()

        while (day < days) {
            processDay(fishes)
            day++
        }

        return fishes.size
    }

    private fun processDay(fishes: MutableList<Int>) {
        val originalLength = fishes.size

        for(i in 0..originalLength - 1) {
            fishes[i]--

            if(fishes[i] < 0) {
                fishes[i] = FISH_TIMER_RESET
                fishes.addLast(FISH_TIMER_NEW)
            }
        }
    }

    private fun parseData() =
        data
            .split(FISH_DELIMITER)
            .map { element ->
                element
                    .trim()
                    .toInt()
            }
            .toList()

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
