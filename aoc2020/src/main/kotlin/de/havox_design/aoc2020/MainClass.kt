package de.havox_design.aoc2020

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2020.day01.RepairReport
import de.havox_design.aoc2020.day02.PasswordPhilosophy
import de.havox_design.aoc2020.day03.TobogganTrajectory
import de.havox_design.aoc2020.day04.PassportProcessing
import de.havox_design.aoc2020.day05.BinaryBoarding
import de.havox_design.aoc2020.day06.CustomCustoms
import de.havox_design.aoc2020.day07.HandyHaversacks
import de.havox_design.aoc2020.day08.Day08
import de.havox_design.aoc2020.day09.Day09
import de.havox_design.aoc2020.day10.Day10
import de.havox_design.aoc2020.day11.Day11
import de.havox_design.aoc2020.day12.Day12
import de.havox_design.aoc2020.day13.Day13
import de.havox_design.aoc2020.day14.Day14
import de.havox_design.aoc2020.day15.Day15
import de.havox_design.aoc2020.day16.Day16
import de.havox_design.aoc2020.day17.Day17
import de.havox_design.aoc2020.day18.Day18
import de.havox_design.aoc2020.day19.Day19
import de.havox_design.aoc2020.day20.Day20
import de.havox_design.aoc2020.day21.Day21
import de.havox_design.aoc2020.day22.Day22
import de.havox_design.aoc2020.day23.Day23
import de.havox_design.aoc2020.day24.Day24
import de.havox_design.aoc2020.day25.Day25

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2020

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            RepairReport(getFileName(day))::processPart1,
            RepairReport(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            PasswordPhilosophy(getFileName(day))::processPart1,
            PasswordPhilosophy(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            TobogganTrajectory(getFileName(day))::processPart1,
            TobogganTrajectory(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            PassportProcessing(getFileName(day))::processPart1,
            PassportProcessing(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            BinaryBoarding(getFileName(day))::processPart1,
            BinaryBoarding(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            CustomCustoms(getFileName(day))::processPart1,
            CustomCustoms(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            HandyHaversacks(getFileName(day))::processPart1,
            HandyHaversacks(getFileName(day))::processPart2,
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
