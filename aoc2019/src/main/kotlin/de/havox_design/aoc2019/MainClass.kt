package de.havox_design.aoc2019

import de.havox_design.aoc.utils.kotlin.AocMainClassHelper
import de.havox_design.aoc2019.day01.Day01
import de.havox_design.aoc2019.day02.Day02
import de.havox_design.aoc2019.day03.Day03
import de.havox_design.aoc2019.day04.Day04
import de.havox_design.aoc2019.day05.Day05
import de.havox_design.aoc2019.day06.Day06
import de.havox_design.aoc2019.day07.Day07
import de.havox_design.aoc2019.day08.Day08
import de.havox_design.aoc2019.day09.Day09
import de.havox_design.aoc2019.day10.Day10
import de.havox_design.aoc2019.day11.Day11
import de.havox_design.aoc2019.day12.Day12
import de.havox_design.aoc2019.day13.Day13
import de.havox_design.aoc2019.day14.Day14
import de.havox_design.aoc2019.day15.Day15
import de.havox_design.aoc2019.day16.Day16
import de.havox_design.aoc2019.day17.Day17
import de.havox_design.aoc2019.day18.Day18
import de.havox_design.aoc2019.day19.Day19
import de.havox_design.aoc2019.day20.Day20
import de.havox_design.aoc2019.day21.Day21
import de.havox_design.aoc2019.day22.Day22
import de.havox_design.aoc2019.day23.Day23
import de.havox_design.aoc2019.day24.Day24
import de.havox_design.aoc2019.day25.Day25

class MainClass: AocMainClassHelper {
    override fun getYear(): Int = 2019

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            Day01(getFileName(day))::processTask1,
            Day01(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            Day02(getFileName(day))::processTask1,
            Day02(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            Day03(getFileName(day))::processTask1,
            Day03(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            Day04(getFileName(day))::processTask1,
            Day04(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            Day05(getFileName(day))::processTask1,
            Day05(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            Day06(getFileName(day))::processTask1,
            Day06(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            Day07(getFileName(day))::processTask1,
            Day07(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            Day08(getFileName(day))::processTask1,
            Day08(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            Day09(getFileName(day))::processTask1,
            Day09(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            Day10(getFileName(day))::processTask1,
            Day10(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            Day11(getFileName(day))::processTask1,
            Day11(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            Day12(getFileName(day))::processTask1,
            Day12(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            Day13(getFileName(day))::processTask1,
            Day13(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            Day14(getFileName(day))::processTask1,
            Day14(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            Day15(getFileName(day))::processTask1,
            Day15(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 16
        day(
            getDayString(day),
            Day16(getFileName(day))::processTask1,
            Day16(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            Day17(getFileName(day))::processTask1,
            Day17(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            Day18(getFileName(day))::processTask1,
            Day18(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 19
        day(
            getDayString(day),
            Day19(getFileName(day))::processTask1,
            Day19(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 20
        day(
            getDayString(day),
            Day20(getFileName(day))::processTask1,
            Day20(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            Day21(getFileName(day))::processTask1,
            Day21(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            Day22(getFileName(day))::processTask1,
            Day22(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 23
        day(
            getDayString(day),
            Day23(getFileName(day))::processTask1,
            Day23(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 24
        day(
            getDayString(day),
            Day24(getFileName(day))::processTask1,
            Day24(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 25
        day(
            getDayString(day),
            Day25(getFileName(day))::processTask1,
            Day25(getFileName(day))::processTask2,
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
