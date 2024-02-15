package de.havox_design.aoc2018

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2018.day01.ChronalCalibration
import de.havox_design.aoc2018.day02.InventoryManagementSystem
import de.havox_design.aoc2018.day03.NoMatterHowYouSliceIt
import de.havox_design.aoc2018.day04.ReposeRecord
import de.havox_design.aoc2018.day05.AlchemicalReduction
import de.havox_design.aoc2018.day06.ChronalCoordinates
import de.havox_design.aoc2018.day07.Day07
import de.havox_design.aoc2018.day08.MemoryManeuver
import de.havox_design.aoc2018.day09.MarbleMania
import de.havox_design.aoc2018.day10.TheStarsAlign
import de.havox_design.aoc2018.day11.ChronalCharge
import de.havox_design.aoc2018.day12.SubterraneanSustainability
import de.havox_design.aoc2018.day13.MineCartMadness
import de.havox_design.aoc2018.day14.ChocolateCharts
import de.havox_design.aoc2018.day15.BeverageBandits
import de.havox_design.aoc2018.day16.ChronalClassification
import de.havox_design.aoc2018.day17.ReservoirResearch
import de.havox_design.aoc2018.day18.Day18
import de.havox_design.aoc2018.day19.Day19
import de.havox_design.aoc2018.day20.Day20
import de.havox_design.aoc2018.day21.Day21
import de.havox_design.aoc2018.day22.Day22
import de.havox_design.aoc2018.day23.Day23
import de.havox_design.aoc2018.day24.Day24
import de.havox_design.aoc2018.day25.Day25

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2018

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            ChronalCalibration(getFileName(day))::processTask1,
            ChronalCalibration(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            InventoryManagementSystem(getFileName(day))::processTask1,
            InventoryManagementSystem(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            NoMatterHowYouSliceIt(getFileName(day))::processTask1,
            NoMatterHowYouSliceIt(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            ReposeRecord(getFileName(day))::processTask1,
            ReposeRecord(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 5
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                AlchemicalReduction(getFileName(day))::processTask1,
                AlchemicalReduction(getFileName(day))::processTask2,
                daysSelected,
                args
            )
        }

        day = 6
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                ChronalCoordinates(getFileName(day))::processTask1,
                ChronalCoordinates(getFileName(day))::processTask2,
                daysSelected,
                args
            )
        }

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
            MemoryManeuver(getFileName(day))::processTask1,
            MemoryManeuver(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            MarbleMania(getFileName(day))::processTask1,
            MarbleMania(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            TheStarsAlign(getFileName(day))::processTask1,
            TheStarsAlign(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            ChronalCharge(getFileName(day))::processTask1,
            ChronalCharge(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            SubterraneanSustainability(getFileName(day))::processTask1,
            SubterraneanSustainability(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            MineCartMadness(getFileName(day))::processTask1,
            MineCartMadness(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            ChocolateCharts(getFileName(day))::processTask1,
            ChocolateCharts(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 15
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                BeverageBandits(getFileName(day))::processTask1,
                BeverageBandits(getFileName(day))::processTask2,
                daysSelected,
                args
            )
        }

        day = 16
        day(
            getDayString(day),
            ChronalClassification(getFileName(day))::processTask1,
            ChronalClassification(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            ReservoirResearch(getFileName(day))::processTask1,
            ReservoirResearch(getFileName(day))::processTask2,
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
