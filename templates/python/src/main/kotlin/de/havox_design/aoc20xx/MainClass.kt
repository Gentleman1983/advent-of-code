package de.havox_design.aoc20xx

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2000

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        for (val day in 1..25) {
            day(
                getDayString(day),
                processRiddle(day, 1),
                processRiddle(day, 2),
                daysSelected,
                args
            )
        }
    }

    private fun processRiddle(day: Int, part: Int): String {
        val year = getYear()
        val riddleDay = getDayString(day)

        return File("aoc${year}-python/src/main/resources/de/havox_design/aoc${year}/day${riddleDay}/day${riddleDay}result_part${part}.txt")
            .readLines()
            .last()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val instance = MainClass()
            instance.processYear(args)
        }
    }
}
