package de.havox_design.aoc2016

import de.havox_design.aoc2016.day01.NoTimeForATaxicab
import de.havox_design.aoc2016.day02.BathroomSecurity
import de.havox_design.aoc2016.day03.SquaresWithThreeSides
import de.havox_design.aoc2016.day04.SecurityThroughObscurity
import de.havox_design.aoc2016.day05.HowAboutANiceGameOfChess
import de.havox_design.aoc2016.day06.SignalsAndNoise
import de.havox_design.aoc2016.day07.InternetProtocolVersion7
import de.havox_design.aoc2016.day08.TwoFactorAuthentication
import de.havox_design.aoc2016.day09.ExplosivesInCyberspace
import de.havox_design.aoc2016.day10.BalanceBots
import de.havox_design.aoc2016.day11.RadioisotopeThermoelectricGenerators
import de.havox_design.aoc2016.day12.LeonardosMonorail
import de.havox_design.aoc2016.day13.AMazeOfTwistyLittleCubicles
import de.havox_design.aoc2016.day14.OneTimePad
import de.havox_design.aoc2016.day15.TimingIsEverything
import de.havox_design.aoc2016.day16.DragonChecksum
import de.havox_design.aoc2016.day17.TwoStepsForward
import de.havox_design.aoc2016.day18.LikeARogue
import de.havox_design.aoc2016.day19.AnElephantNamedJoseph
import de.havox_design.aoc2016.day20.FirewallRules
import de.havox_design.aoc2016.day21.ScrambledLettersAndHash
import de.havox_design.aoc2016.day22.GridComputing
import de.havox_design.aoc2016.day23.SafeCracking
import de.havox_design.aoc2016.day24.AirDuctSpelunking
import de.havox_design.aoc2016.day25.ClockSignal

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val daysSelected = args
                .any { argument -> argument.startsWith("day") }

            var day = 1
            day(
                getDayString(day),
                NoTimeForATaxicab(getFileName(day))::solvePart1,
                NoTimeForATaxicab(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 2
            day(
                getDayString(day),
                BathroomSecurity(getFileName(day))::solvePart1,
                BathroomSecurity(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 3
            day(
                getDayString(day),
                SquaresWithThreeSides(getFileName(day))::solvePart1,
                SquaresWithThreeSides(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 4
            day(
                getDayString(day),
                SecurityThroughObscurity(getFileName(day))::solvePart1,
                SecurityThroughObscurity(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 5
            day(
                getDayString(day),
                HowAboutANiceGameOfChess(getFileName(day))::solvePart1,
                HowAboutANiceGameOfChess(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 6
            day(
                getDayString(day),
                SignalsAndNoise(getFileName(day))::solvePart1,
                SignalsAndNoise(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 7
            day(
                getDayString(day),
                InternetProtocolVersion7(getFileName(day))::solvePart1,
                InternetProtocolVersion7(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 8
            day(
                getDayString(day),
                TwoFactorAuthentication(getFileName(day))::solvePart1,
                TwoFactorAuthentication(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 9
            day(
                getDayString(day),
                ExplosivesInCyberspace(getFileName(day))::solvePart1,
                ExplosivesInCyberspace(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 10
            day(
                getDayString(day),
                ::balanceBotsPart1,
                BalanceBots(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 11
            day(
                getDayString(day),
                RadioisotopeThermoelectricGenerators(getFileName(day))::solvePart1,
                RadioisotopeThermoelectricGenerators(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 12
            if (!args.contains("testing")) {
                day(
                    getDayString(day),
                    LeonardosMonorail(getFileName(day))::solvePart1,
                    LeonardosMonorail(getFileName(day))::solvePart2,
                    daysSelected,
                    args
                )
            }

            day = 13
            day(
                getDayString(day),
                ::aMazeOfTwistyLittleCubiclesPart1,
                ::aMazeOfTwistyLittleCubiclesPart2,
                daysSelected,
                args
            )

            day = 14
            day(
                getDayString(day),
                OneTimePad(getFileName(day))::solvePart1,
                OneTimePad(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 15
            day(
                getDayString(day),
                TimingIsEverything(getFileName(day))::solvePart1,
                TimingIsEverything(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 16
            day(
                getDayString(day),
                ::dragonChecksumPart1,
                ::dragonChecksumPart2,
                daysSelected,
                args
            )

            day = 17
            day(
                getDayString(day),
                TwoStepsForward(getFileName(day))::solvePart1,
                TwoStepsForward(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 18
            day(
                getDayString(day),
                ::likeARoguePart1,
                LikeARogue(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 19
            day(
                getDayString(day),
                AnElephantNamedJoseph(getFileName(day))::solvePart1,
                AnElephantNamedJoseph(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 20
            day(
                getDayString(day),
                FirewallRules(getFileName(day))::solvePart1,
                FirewallRules(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 21
            day(
                getDayString(day),
                ::scrambledLettersAndHashPart1,
                ::scrambledLettersAndHashPart2,
                daysSelected,
                args
            )

            day = 22
            day(
                getDayString(day),
                GridComputing(getFileName(day))::solvePart1,
                GridComputing(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 23
            if (!args.contains("testing")) {
                day(
                    getDayString(day),
                    SafeCracking(getFileName(day))::solvePart1,
                    SafeCracking(getFileName(day))::solvePart2,
                    daysSelected,
                    args
                )
            }

            day = 24
            day(
                getDayString(day),
                AirDuctSpelunking(getFileName(day))::solvePart1,
                AirDuctSpelunking(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 25
            day(
                getDayString(day),
                ClockSignal(getFileName(day))::solvePart1,
                ClockSignal(getFileName(day))::solvePart2,
                daysSelected,
                args
            )
        }

        private fun balanceBotsPart1(): String {
            val filename = getFileName(10)

            return BalanceBots.solvePart1(filename).toString()
        }

        private fun aMazeOfTwistyLittleCubiclesPart1(): String {
            val filename = getFileName(13)

            return AMazeOfTwistyLittleCubicles.solvePart1(filename).toString()
        }

        private fun aMazeOfTwistyLittleCubiclesPart2(): String {
            val filename = getFileName(13)

            return AMazeOfTwistyLittleCubicles.solvePart2(filename).toString()
        }

        private fun dragonChecksumPart1(): String {
            val filename = getFileName(16)

            return DragonChecksum.solvePart1(filename).toString()
        }

        private fun dragonChecksumPart2(): String {
            val filename = getFileName(16)

            return DragonChecksum.solvePart2(filename).toString()
        }

        private fun likeARoguePart1(): String {
            val filename = getFileName(18)

            return LikeARogue.solvePart1(filename).toString()
        }

        private fun scrambledLettersAndHashPart1(): String {
            val filename = getFileName(21)

            return ScrambledLettersAndHash.solvePart1(filename).toString()
        }

        private fun scrambledLettersAndHashPart2(): String {
            val filename = getFileName(21)

            return ScrambledLettersAndHash.solvePart2(filename).toString()
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
            val year = 2016
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
