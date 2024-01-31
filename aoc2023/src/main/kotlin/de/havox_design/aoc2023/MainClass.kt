package de.havox_design.aoc2023

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2023.day01.Trebuchet
import de.havox_design.aoc2023.day02.CubeConundrum
import de.havox_design.aoc2023.day03.GearRatios
import de.havox_design.aoc2023.day04.Scratchcards
import de.havox_design.aoc2023.day05.IfYouGiveASeedAFertilizer
import de.havox_design.aoc2023.day06.WaitForIt
import de.havox_design.aoc2023.day07.CamelCards
import de.havox_design.aoc2023.day08.HauntedWasteland
import de.havox_design.aoc2023.day09.MirageMaintenance
import de.havox_design.aoc2023.day10.PipeMaze
import de.havox_design.aoc2023.day11.CosmicExpansion
import de.havox_design.aoc2023.day12.HotSprings
import de.havox_design.aoc2023.day13.PointOfIncidence
import de.havox_design.aoc2023.day14.ParabolicReflectorDish
import de.havox_design.aoc2023.day15.LensLibrary
import de.havox_design.aoc2023.day16.TheFloorWillBeLava
import de.havox_design.aoc2023.day17.ClumsyCrucible
import de.havox_design.aoc2023.day18.LavaductLagoon
import de.havox_design.aoc2023.day19.Aplenty
import de.havox_design.aoc2023.day20.PulsePropagation
import de.havox_design.aoc2023.day21.StepCounter
import de.havox_design.aoc2023.day22.SandSlabs
import de.havox_design.aoc2023.day23.ALongWalk
import de.havox_design.aoc2023.day24.NeverTellMeTheOdds
import de.havox_design.aoc2023.day25.Snowverload

class MainClass: AocMainClassHelper {
    override fun getYear(): Int = 2023

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            Trebuchet(getFileName(day))::solvePart1,
            Trebuchet(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            CubeConundrum(getFileName(day))::solvePart1,
            CubeConundrum(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            GearRatios(getFileName(day))::solvePart1,
            GearRatios(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            Scratchcards(getFileName(day))::solvePart1,
            Scratchcards(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 5
        if (!args.contains("testing")) {
            if (args.contains("day05")) { // 9min 15sec on Ryzen 7 5700G
                println("Calculation of solution 2 may take some minutes...")
            }
            day(
                getDayString(day),
                IfYouGiveASeedAFertilizer(getFileName(day))::solvePart1,
                IfYouGiveASeedAFertilizer(getFileName(day))::solvePart2,
                daysSelected,
                args
            )
        }

        day = 6
        day(
            getDayString(day),
            WaitForIt(getFileName(day))::solvePart1,
            WaitForIt(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            CamelCards(getFileName(day))::solvePart1,
            CamelCards(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            HauntedWasteland(getFileName(day))::solvePart1,
            HauntedWasteland(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            MirageMaintenance(getFileName(day))::solvePart1,
            MirageMaintenance(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            PipeMaze(getFileName(day))::solvePart1,
            PipeMaze(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            CosmicExpansion(getFileName(day))::solvePart1,
            CosmicExpansion(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            HotSprings(getFileName(day))::solvePart1,
            HotSprings(getFileName(day))::quicksolvePart2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            PointOfIncidence(getFileName(day))::solvePart1,
            PointOfIncidence(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            ParabolicReflectorDish(getFileName(day))::solvePart1,
            ParabolicReflectorDish(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            LensLibrary(getFileName(day))::solvePart1,
            LensLibrary(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 16
        day(
            getDayString(day),
            TheFloorWillBeLava(getFileName(day))::solvePart1,
            TheFloorWillBeLava(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            ClumsyCrucible(getFileName(day))::solvePart1,
            ClumsyCrucible(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            LavaductLagoon(getFileName(day))::solvePart1,
            LavaductLagoon(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 19
        day(
            getDayString(day),
            Aplenty(getFileName(day))::solvePart1,
            Aplenty(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 20
        day(
            getDayString(day),
            PulsePropagation(getFileName(day))::solvePart1,
            PulsePropagation(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            StepCounter(getFileName(day))::solvePart1,
            StepCounter(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 22
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                SandSlabs(getFileName(day))::solvePart1,
                SandSlabs(getFileName(day))::solvePart2,
                daysSelected,
                args
            )
        }

        day = 23
        if (!args.contains("testing")) {
            day(
                getDayString(day),
                ALongWalk(getFileName(day))::solvePart1,
                ALongWalk(getFileName(day))::solvePart2,
                daysSelected,
                args
            )
        }

        day = 24
        day(
            getDayString(day),
            NeverTellMeTheOdds(getFileName(day))::solvePart1,
            NeverTellMeTheOdds(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 25
        day(
            getDayString(day),
            Snowverload(getFileName(day))::solvePart1,
            Snowverload(getFileName(day))::solvePart2,
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
