package de.havox_design.aoc2015

import de.havox_design.aoc2015.day01.NotQuiteLisp
import de.havox_design.aoc2015.day02.WrappingPaper
import de.havox_design.aoc2015.day03.PresentDelivery
import de.havox_design.aoc2015.day04.AdventCoins
import de.havox_design.aoc2015.day05.NiceStrings
import de.havox_design.aoc2015.day06.HouseDecoratingContest
import de.havox_design.aoc2015.day07.LogicGates
import de.havox_design.aoc2015.day08.Matchsticks
import de.havox_design.aoc2015.day09.AllInASingleNight
import de.havox_design.aoc2015.day10.Elves
import de.havox_design.aoc2015.day11.SantasPasswordPolicy
import de.havox_design.aoc2015.day12.JSONAbacus
import de.havox_design.aoc2015.day13.KnightsOfTheDinnerTable
import de.havox_design.aoc2015.day14.ReindeerOlympics
import de.havox_design.aoc2015.day15.ScienceForHungryPeople
import de.havox_design.aoc2015.day16.FindAuntSue
import de.havox_design.aoc2015.day17.NotEnoughEggnod
import de.havox_design.aoc2015.day18.GIFMatrix
import de.havox_design.aoc2015.day19.MedicineForRudolph
import de.havox_design.aoc2015.day20.PacketDelivery
import de.havox_design.aoc2015.day21.RPGBossFight
import de.havox_design.aoc2015.day22.RPGWizardFight
import de.havox_design.aoc2015.day23.Turing
import de.havox_design.aoc2015.day24.BalancedQuantumEntanglement
import de.havox_design.aoc2015.day25.CodeLock

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val daysSelected = args
                .any { argument -> argument.startsWith("day") }

            var day = 1
            day(
                getDayString(day),
                NotQuiteLisp(getFileName(day))::processTask1,
                NotQuiteLisp(getFileName(day))::processTask2,
                daysSelected,
                args
            )

            day = 2
            day(
                getDayString(day),
                WrappingPaper(getFileName(day))::solvePart1,
                WrappingPaper(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 3
            day(
                getDayString(day),
                PresentDelivery(getFileName(day))::solvePart1,
                PresentDelivery(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 4
            day(
                getDayString(day),
                AdventCoins(getFileName(day))::solvePart1,
                AdventCoins(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 5
            day(
                getDayString(day),
                NiceStrings(getFileName(day))::solvePart1,
                NiceStrings(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 6
            day(
                getDayString(day),
                HouseDecoratingContest(getFileName(day))::solvePart1,
                HouseDecoratingContest(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 7
            day(
                getDayString(day),
                LogicGates(getFileName(day))::processPart1,
                LogicGates(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 8
            day(
                getDayString(day),
                Matchsticks(getFileName(day))::solvePart1,
                Matchsticks(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 9
            day(
                getDayString(day),
                AllInASingleNight(getFileName(day))::solvePart1,
                AllInASingleNight(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 10
            day(
                getDayString(day),
                Elves(getFileName(day))::solvePart1,
                Elves(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 11
            day(
                getDayString(day),
                SantasPasswordPolicy(getFileName(day))::solvePart1,
                SantasPasswordPolicy(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 12
            day(
                getDayString(day),
                JSONAbacus(getFileName(day))::solvePart1,
                JSONAbacus(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 13
            day(
                getDayString(day),
                KnightsOfTheDinnerTable(getFileName(day))::processPart1,
                KnightsOfTheDinnerTable(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 14
            day(
                getDayString(day),
                ReindeerOlympics(getFileName(day))::solvePart1,
                ReindeerOlympics(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 15
            day(
                getDayString(day),
                ScienceForHungryPeople(getFileName(day))::solvePart1,
                ScienceForHungryPeople(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 16
            day(
                getDayString(day),
                FindAuntSue(getFileName(day))::processPart1,
                FindAuntSue(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 17
            day(
                getDayString(day),
                NotEnoughEggnod(getFileName(day))::solvePart1,
                NotEnoughEggnod(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 18
            day(
                getDayString(day),
                GIFMatrix(getFileName(day))::processPart1,
                GIFMatrix(getFileName(day))::processPart2,
                daysSelected,
                args
            )

            day = 19
            day(
                getDayString(day),
                MedicineForRudolph(getFileName(day))::processPart1,
                ::medicineForRudolphPart2,
                daysSelected,
                args
            )

            day = 20
            day(
                getDayString(day),
                PacketDelivery(getFileName(day))::solvePart1,
                PacketDelivery(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 21
            day(
                getDayString(day),
                RPGBossFight(getFileName(day))::solvePart1,
                RPGBossFight(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 22
            day(
                getDayString(day),
                RPGWizardFight(getFileName(day))::solvePart1,
                RPGWizardFight(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 23
            day(
                getDayString(day),
                Turing(getFileName(day))::solvePart1,
                Turing(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 24
            day(
                getDayString(day),
                BalancedQuantumEntanglement(getFileName(day))::solvePart1,
                BalancedQuantumEntanglement(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 25
            day(
                getDayString(day),
                CodeLock(getFileName(day))::solvePart1,
                CodeLock(getFileName(day))::solvePart2,
                daysSelected,
                args
            )
        }

        private fun medicineForRudolphPart2(): String {
            val filename = getFileName(19)

            return MedicineForRudolph(filename)
                .getPythonResult("de/havox_design/aoc2015/day19/day19result_part2.txt")
        }

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
            val year = 2015
            var dayString = day.toString()

            if (day < 10) {
                dayString = "0$dayString"
            }

            return "de/havox_design/aoc${year}/day${dayString}/day${dayString}.txt"
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
