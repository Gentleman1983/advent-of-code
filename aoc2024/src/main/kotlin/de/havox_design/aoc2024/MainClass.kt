package de.havox_design.aoc2024

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2024.day01.HistorianHysteria
import de.havox_design.aoc2024.day02.RedNosedReports
import de.havox_design.aoc2024.day03.MullItOver
import de.havox_design.aoc2024.day04.CeresSearch
import de.havox_design.aoc2024.day05.PrintQueue
import de.havox_design.aoc2024.day06.GuardGallivant
import de.havox_design.aoc2024.day07.BridgeRepair
import de.havox_design.aoc2024.day08.ResonantCollinearity
import de.havox_design.aoc2024.day09.DiskFragmenter
import de.havox_design.aoc2024.day10.HoofIt
import de.havox_design.aoc2024.day11.PlutonianPebbles
import de.havox_design.aoc2024.day12.GardenGroups
import de.havox_design.aoc2024.day13.ClawContraption
import de.havox_design.aoc2024.day14.RestroomRedoubt
import de.havox_design.aoc2024.day15.WarehouseWoes
import de.havox_design.aoc2024.day16.ReindeerMaze
import de.havox_design.aoc2024.day17.ChronospatialComputer
import de.havox_design.aoc2024.day18.RAMRun
import de.havox_design.aoc2024.day19.LinenLayout
import de.havox_design.aoc2024.day20.RaceCondition
import de.havox_design.aoc2024.day21.KeypadConundrum
import de.havox_design.aoc2024.day22.MonkeyMarket
import de.havox_design.aoc2024.day23.Day23
import de.havox_design.aoc2024.day24.Day24
import de.havox_design.aoc2024.day25.Day25

class MainClass : AocMainClassHelper {
    override fun getYear(): Int = 2024

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            HistorianHysteria(getFileName(day))::processPart1,
            HistorianHysteria(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            RedNosedReports(getFileName(day))::processPart1,
            RedNosedReports(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            MullItOver(getFileName(day))::processPart1,
            MullItOver(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            CeresSearch(getFileName(day))::processPart1,
            CeresSearch(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            PrintQueue(getFileName(day))::processPart1,
            PrintQueue(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            GuardGallivant(getFileName(day))::processPart1,
            GuardGallivant(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            BridgeRepair(getFileName(day))::processPart1,
            BridgeRepair(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            ResonantCollinearity(getFileName(day))::processPart1,
            ResonantCollinearity(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 9
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                DiskFragmenter(getFileName(day))::processPart1,
                DiskFragmenter(getFileName(day))::processPart2,
                daysSelected,
                args
            )
        }

        day = 10
        day(
            getDayString(day),
            HoofIt(getFileName(day))::processPart1,
            HoofIt(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            PlutonianPebbles(getFileName(day))::processPart1,
            PlutonianPebbles(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            GardenGroups(getFileName(day))::processPart1,
            GardenGroups(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            ClawContraption(getFileName(day))::processPart1,
            ClawContraption(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            RestroomRedoubt(getFileName(day))::processPart1,
            RestroomRedoubt(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            WarehouseWoes(getFileName(day))::processPart1,
            WarehouseWoes(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 16
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                ReindeerMaze(getFileName(day))::processPart1,
                ReindeerMaze(getFileName(day))::processPart2,
                daysSelected,
                args
            )
        }

        day = 17
        day(
            getDayString(day),
            ChronospatialComputer(getFileName(day))::processPart1,
            ChronospatialComputer(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            RAMRun(getFileName(day))::processPart1,
            RAMRun(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 19
        day(
            getDayString(day),
            LinenLayout(getFileName(day))::processPart1,
            LinenLayout(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 20
        day(
            getDayString(day),
            RaceCondition(getFileName(day))::processPart1,
            RaceCondition(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            KeypadConundrum(getFileName(day))::processPart1,
            KeypadConundrum(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            MonkeyMarket(getFileName(day))::processPart1,
            MonkeyMarket(getFileName(day))::processPart2,
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
