package de.havox_design.aoc20xx

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc20xx.day01.Day01
import de.havox_design.aoc20xx.day02.Day02
import de.havox_design.aoc20xx.day03.Day03
import de.havox_design.aoc20xx.day04.Day04
import de.havox_design.aoc20xx.day05.Day05
import de.havox_design.aoc20xx.day06.Day06
import de.havox_design.aoc20xx.day07.Day07
import de.havox_design.aoc20xx.day08.Day08
import de.havox_design.aoc20xx.day09.Day09
import de.havox_design.aoc20xx.day10.Day10
import de.havox_design.aoc20xx.day11.Day11
import de.havox_design.aoc20xx.day12.Day12
import de.havox_design.aoc20xx.day13.Day13
import de.havox_design.aoc20xx.day14.Day14
import de.havox_design.aoc20xx.day15.Day15
import de.havox_design.aoc20xx.day16.Day16
import de.havox_design.aoc20xx.day17.Day17
import de.havox_design.aoc20xx.day18.Day18
import de.havox_design.aoc20xx.day19.Day19
import de.havox_design.aoc20xx.day20.Day20
import de.havox_design.aoc20xx.day21.Day21
import de.havox_design.aoc20xx.day22.Day22
import de.havox_design.aoc20xx.day23.Day23
import de.havox_design.aoc20xx.day24.Day24
import de.havox_design.aoc20xx.day25.Day25

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2000

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            Day01.solvePart1(getFileName(day)).toString(),
            Day01.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            Day02.solvePart1(getFileName(day)).toString(),
            Day02.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            Day03.solvePart1(getFileName(day)).toString(),
            Day03.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            Day04.solvePart1(getFileName(day)).toString(),
            Day04.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            Day05.solvePart1(getFileName(day)).toString(),
            Day05.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            Day06.solvePart1(getFileName(day)).toString(),
            Day06.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            Day07.solvePart1(getFileName(day)).toString(),
            Day07.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            Day08.solvePart1(getFileName(day)).toString(),
            Day08.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            Day09.solvePart1(getFileName(day)).toString(),
            Day09.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            Day10.solvePart1(getFileName(day)).toString(),
            Day10.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            Day11.solvePart1(getFileName(day)).toString(),
            Day11.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            Day12.solvePart1(getFileName(day)).toString(),
            Day12.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            Day13.solvePart1(getFileName(day)).toString(),
            Day13.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            Day14.solvePart1(getFileName(day)).toString(),
            Day14.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            Day15.solvePart1(getFileName(day)).toString(),
            Day15.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 16
        day(
            getDayString(day),
            Day16.solvePart1(getFileName(day)).toString(),
            Day16.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            Day17.solvePart1(getFileName(day)).toString(),
            Day17.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            Day18.solvePart1(getFileName(day)).toString(),
            Day18.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 19
        day(
            getDayString(day),
            Day19.solvePart1(getFileName(day)).toString(),
            Day19.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 20
        day(
            getDayString(day),
            Day20.solvePart1(getFileName(day)).toString(),
            Day20.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            Day21.solvePart1(getFileName(day)).toString(),
            Day21.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            Day22.solvePart1(getFileName(day)).toString(),
            Day22.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 23
        day(
            getDayString(day),
            Day23.solvePart1(getFileName(day)).toString(),
            Day23.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 24
        day(
            getDayString(day),
            Day24.solvePart1(getFileName(day)).toString(),
            Day24.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )

        day = 25
        day(
            getDayString(day),
            Day25.solvePart1(getFileName(day)).toString(),
            Day25.solvePart2(getFileName(day)).toString(),
            daysSelected,
            args
        )
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val instance = MainClass()
            instance.processYear(args)
        }
    }
}
