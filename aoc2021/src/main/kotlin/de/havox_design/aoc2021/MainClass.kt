package de.havox_design.aoc2021

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2021.day01.Day01
import de.havox_design.aoc2021.day02.Dive
import de.havox_design.aoc2021.day03.BinaryDiagnostic
import de.havox_design.aoc2021.day04.GiantSquid
import de.havox_design.aoc2021.day05.HydrothermalVenture
import de.havox_design.aoc2021.day06.Day06
import de.havox_design.aoc2021.day07.Day07
import de.havox_design.aoc2021.day08.Day08
import de.havox_design.aoc2021.day09.Day09
import de.havox_design.aoc2021.day10.Day10
import de.havox_design.aoc2021.day11.Day11
import de.havox_design.aoc2021.day12.Day12
import de.havox_design.aoc2021.day13.Day13
import de.havox_design.aoc2021.day14.Day14
import de.havox_design.aoc2021.day15.Day15
import de.havox_design.aoc2021.day16.Day16
import de.havox_design.aoc2021.day17.Day17
import de.havox_design.aoc2021.day18.Day18
import de.havox_design.aoc2021.day19.Day19
import de.havox_design.aoc2021.day20.Day20
import de.havox_design.aoc2021.day21.Day21
import de.havox_design.aoc2021.day22.Day22
import de.havox_design.aoc2021.day23.Day23
import de.havox_design.aoc2021.day24.Day24
import de.havox_design.aoc2021.day25.Day25

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2021

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            Day01(getFileName(day))::processPart1,
            Day01(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            Dive(getFileName(day))::processPart1,
            Dive(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            BinaryDiagnostic(getFileName(day))::processPart1,
            BinaryDiagnostic(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            GiantSquid(getFileName(day))::processPart1,
            GiantSquid(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            HydrothermalVenture(getFileName(day))::processPart1,
            HydrothermalVenture(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            Day06(getFileName(day))::processPart1,
            Day06(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            Day07(getFileName(day))::processPart1,
            Day07(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            Day08(getFileName(day))::processPart1,
            Day08(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            Day09(getFileName(day))::processPart1,
            Day09(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            Day10(getFileName(day))::processPart1,
            Day10(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            Day11(getFileName(day))::processPart1,
            Day11(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            Day12(getFileName(day))::processPart1,
            Day12(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            Day13(getFileName(day))::processPart1,
            Day13(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            Day14(getFileName(day))::processPart1,
            Day14(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            Day15(getFileName(day))::processPart1,
            Day15(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 16
        day(
            getDayString(day),
            Day16(getFileName(day))::processPart1,
            Day16(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            Day17(getFileName(day))::processPart1,
            Day17(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            Day18(getFileName(day))::processPart1,
            Day18(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 19
            day(
                getDayString(day),
                Day19(getFileName(day))::processPart1,
                Day19(getFileName(day))::processPart2,
                daysSelected,
                args
            )

        day = 20
        day(
            getDayString(day),
            Day20(getFileName(day))::processPart1,
            Day20(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            Day21(getFileName(day))::processPart1,
            Day21(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            Day22(getFileName(day))::processPart1,
            Day22(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 23
        day(
            getDayString(day),
            Day23(getFileName(day))::processPart1,
            Day23(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 24
        day(
            getDayString(day),
            Day24(getFileName(day))::processPart1,
            Day24(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 25
        day(
            getDayString(day),
            Day25(getFileName(day))::processPart1,
            Day25(getFileName(day))::processPart2,
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
