package de.havox_design.aoc2022

import de.havox_design.aoc2022.day01.CaloriesCounter
import de.havox_design.aoc2022.day02.RockPaperScissorsGame
import de.havox_design.aoc2022.day03.RucksackReorganization
import de.havox_design.aoc2022.day04.CampCleanup
import de.havox_design.aoc2022.day05.SupplyStacks
import de.havox_design.aoc2022.day06.TuningTrouble
import de.havox_design.aoc2022.day07.NoSpaceLeftOnDevice
import de.havox_design.aoc2022.day08.TreetopTreeHouse
import de.havox_design.aoc2022.day09.RopeBridge
import de.havox_design.aoc2022.day10.CathodeRayTube
import de.havox_design.aoc2022.day11.MonkeyInTheMiddle
import de.havox_design.aoc2022.day11.ScalaMonkeyInTheMiddle
import de.havox_design.aoc2022.day12.HillClimbingAlgorithm
import de.havox_design.aoc2022.day13.DistressSignal
import de.havox_design.aoc2022.day14.RegolithReservoir
import de.havox_design.aoc2022.day15.BeaconExclusionZone
import de.havox_design.aoc2022.day16.ProboscideaVolcanium
import de.havox_design.aoc2022.day16.ScalaProboscideaVolcanium
import de.havox_design.aoc2022.day17.PyroclasticFlow
import de.havox_design.aoc2022.day17.ScalaPyroclasticFlow
import de.havox_design.aoc2022.day18.BoilingBoulders
import de.havox_design.aoc2022.day19.NotEnoughMinerals
import de.havox_design.aoc2022.day20.GrovePositioningSystem
import de.havox_design.aoc2022.day21.MonkeyMath
import de.havox_design.aoc2022.day22.MonkeyMap
import de.havox_design.aoc2022.day23.UnstableDiffusion
import de.havox_design.aoc2022.day24.BlizzardBasin
import de.havox_design.aoc2022.day25.FullOfHotAir
import de.havox_design.aoc2022.day25.SNAFUNumber
import de.havox_design.aoc2022.day25.toReadableString

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val daysSelected = args
                .any { argument -> argument.startsWith("day") }

            var day = 1
            day(
                getDayString(day),
                CaloriesCounter(getFileName(day))::processFile,
                CaloriesCounter(getFileName(day))::processFileTopThree,
                daysSelected,
                args
            )

            day = 2
            day(
                getDayString(day),
                RockPaperScissorsGame(getFileName(day))::getResultForGuide,
                RockPaperScissorsGame(getFileName(day))::getResultForExpectedResult,
                daysSelected,
                args
            )

            day = 3
            day(
                getDayString(day),
                RucksackReorganization(getFileName(day))::getDuplicatesScoreFromList,
                RucksackReorganization(getFileName(day))::getBadgesScoreFromList,
                daysSelected,
                args
            )

            day = 4
            day(
                getDayString(day),
                CampCleanup(getFileName(day))::findAssignmentPairsWithOneAssignmentContainingTheOther,
                CampCleanup(getFileName(day))::findAssignmentPairsWithOneAssignmentOverlappingTheOther,
                daysSelected,
                args
            )

            day = 5
            day(
                getDayString(day),
                SupplyStacks(getFileName(day))::evaluateTask1,
                SupplyStacks(getFileName(day))::evaluateTask2,
                daysSelected,
                args
            )

            day = 6
            day(
                getDayString(day),
                TuningTrouble(getFileName(day))::processPart1,
                TuningTrouble(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 7
            day(
                getDayString(day),
                NoSpaceLeftOnDevice(getFileName(day))::processPart1,
                NoSpaceLeftOnDevice(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 8
            day(
                getDayString(day),
                TreetopTreeHouse(getFileName(day))::processPart1,
                TreetopTreeHouse(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 9
            day(
                getDayString(day),
                RopeBridge(getFileName(day))::processPart1,
                RopeBridge(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 10
            day(
                getDayString(day),
                CathodeRayTube(getFileName(day))::processPart1,
                ::crtPreview,
                daysSelected,
                args
            )

            day = 11
            day(
                getDayString(day),
                MonkeyInTheMiddle(getFileName(day))::processPart1,
                ::solveDay11Part2,
                daysSelected,
                args
            )

            day = 12
            day(
                getDayString(day),
                HillClimbingAlgorithm(getFileName(day))::processPart1,
                HillClimbingAlgorithm(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 13
            day(
                getDayString(day),
                DistressSignal(getFileName(day))::processPart1,
                DistressSignal(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 14
            day(
                getDayString(day),
                RegolithReservoir(getFileName(day))::processPart1,
                RegolithReservoir(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 15
            day(
                getDayString(day),
                BeaconExclusionZone(getFileName(day))::processPart1,
                BeaconExclusionZone(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 16
            day(
                getDayString(day),
                ProboscideaVolcanium(getFileName(day))::processPart1,
                ::solveDay16Part2,
                daysSelected,
                args
            )

            day = 17
            day(
                getDayString(day),
                PyroclasticFlow(getFileName(day))::processPart1,
                ::solveDay17Part2,
                daysSelected,
                args
            )

            day = 18
            day(
                getDayString(day),
                BoilingBoulders(getFileName(day))::processPart1,
                BoilingBoulders(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 19
            if (!args.contains("testing")) {
                day(
                    getDayString(day),
                    NotEnoughMinerals(getFileName(day))::processPart1,
                    NotEnoughMinerals(getFileName(day))::processPart2,
                    daysSelected,
                    args
                )
            }

            day = 20
            day(
                getDayString(day),
                GrovePositioningSystem(getFileName(day))::processPart1,
                GrovePositioningSystem(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 21
            day(
                getDayString(day),
                MonkeyMath(getFileName(day).replace("21.txt", "21Part1.txt"))::processPart1,
                MonkeyMath(getFileName(day).replace("21.txt", "21Part2.txt"))::processPart2,
                daysSelected,
                args
            )

            day = 22
            day(
                getDayString(day),
                MonkeyMap(getFileName(day))::processPart1,
                MonkeyMap(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 23
            day(
                getDayString(day),
                UnstableDiffusion(getFileName(day))::processPart1,
                UnstableDiffusion(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 24
            day(
                getDayString(day),
                BlizzardBasin(getFileName(day))::processPart1,
                ::blizzardBasinPart2,
                daysSelected,
                args
            )

            day = 25
            day(
                getDayString(day),
                ::calculateSNAFU,
                ::merryXMAS,
                daysSelected,
                args
            )
        }

        private fun solveDay11Part2(): String {
            return ScalaMonkeyInTheMiddle.solvePart2(getFileName(11), 10000).toString()
        }

        private fun solveDay16Part2(): String {
            return ScalaProboscideaVolcanium.solvePart2(getFileName(16), 10000).toString()
        }

        private fun solveDay17Part2(): String {
            return ScalaPyroclasticFlow.solvePart2(getFileName(17), 10000).toString()
        }

        private fun crtPreview(): String {
            val filename = getFileName(10)

            return "\nCRT preview:\n" +
                    "===========\n" +
                    "${CathodeRayTube(filename).processPart2()}"
        }

        private fun blizzardBasinPart2(): String {
            val filename = getFileName(24)

            return "\nFirst way to goal took: ${BlizzardBasin(filename).processPart2()[0]} minutes\n" +
                    "Way back to start took: ${BlizzardBasin(filename).processPart2()[1]} minutes\n" +
                    "Way back to goal took: ${BlizzardBasin(filename).processPart2()[2]} minutes\n" +
                    "Total minutes needed: ${BlizzardBasin(filename).processPart2()[3]}"
        }

        private fun calculateSNAFU(): String {
            val filename = getFileName(25)
            val part1Result = FullOfHotAir(filename).processPart1()

            return "SNAFU number: ${SNAFUNumber.toSnafu(part1Result).toReadableString()} ($part1Result)"
        }

        private fun merryXMAS(): String =
            "Merry XMAS!"

        private fun day(
            day: String,
            solutionPart1: () -> Any,
            solutionPart2: () -> Any,
            daysSelected: Boolean,
            args: Array<String>
        ) {
            if (!daysSelected || args.contains(day.lowercase())) {
                solveDay(day, solutionPart1.invoke(), solutionPart2.invoke())
            }
        }

        private fun solveDay(day: String, solutionPart1: Any, solutionPart2: Any) {
            println("${day}: Solution part 1: $solutionPart1")
            println("${day}: Solution part 2: $solutionPart2")
        }

        private fun getFileName(day: Int): String {
            var dayString = day.toString()
            if (day < 10) {
                dayString = "0$dayString"
            }

            return "de/havox_design/aoc2022/day${dayString}/day${dayString}.txt"
        }

        private fun getDayString(day: Int): String {
            var dayString = day.toString()
            if (day < 10) {
                dayString = "0$dayString"
            }

            return "Day${dayString}"
        }
    }
}
