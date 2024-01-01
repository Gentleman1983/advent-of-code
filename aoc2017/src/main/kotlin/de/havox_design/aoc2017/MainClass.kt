package de.havox_design.aoc2017

import de.havox_design.aoc2017.day01.InverseCaptcha
import de.havox_design.aoc2017.day02.CorruptionChecksum
import de.havox_design.aoc2017.day03.SpiralMemory
import de.havox_design.aoc2017.day04.HighEntropyPassphrases
import de.havox_design.aoc2017.day05.AMazeOfTwistyTrampolinesAllAlike
import de.havox_design.aoc2017.day06.MemoryReallocation
import de.havox_design.aoc2017.day07.RecursiveCircus
import de.havox_design.aoc2017.day08.IHeardYouLikeRegisters
import de.havox_design.aoc2017.day09.StreamProcessing
import de.havox_design.aoc2017.day10.KnotHash
import de.havox_design.aoc2017.day11.HexEd
import de.havox_design.aoc2017.day12.DigitalPlumber
import de.havox_design.aoc2017.day13.PacketScanners
import de.havox_design.aoc2017.day14.DiskDefragmentation
import de.havox_design.aoc2017.day15.DuelingGenerators
import de.havox_design.aoc2017.day16.PermutationPromenade
import de.havox_design.aoc2017.day17.SpinLock
import de.havox_design.aoc2017.day18.Duet
import de.havox_design.aoc2017.day19.ASeriesOfTubes
import de.havox_design.aoc2017.day20.ParticleSwarm
import de.havox_design.aoc2017.day21.FractalArt
import de.havox_design.aoc2017.day22.SporificaVirus
import de.havox_design.aoc2017.day23.CoprocessorConflagration
import de.havox_design.aoc2017.day24.ElectromagneticMoat
import de.havox_design.aoc2017.day25.TheHaltingProblem

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val daysSelected = args
                .any { argument -> argument.startsWith("day") }

            var day = 1
            day(
                getDayString(day),
                InverseCaptcha(getFileName(day))::solvePart1,
                InverseCaptcha(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 2
            day(
                getDayString(day),
                CorruptionChecksum(getFileName(day))::solvePart1,
                CorruptionChecksum(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 3
            day(
                getDayString(day),
                SpiralMemory(getFileName(day))::solvePart1,
                SpiralMemory(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 4
            day(
                getDayString(day),
                Companion::highEntropyPassphrasesPart1,
                Companion::highEntropyPassphrasesPart2,
                daysSelected,
                args
            )

            day = 5
            day(
                getDayString(day),
                Companion::aMazeOfTwistyTrampolinesAllAlikePart1,
                Companion::aMazeOfTwistyTrampolinesAllAlikePart2,
                daysSelected,
                args
            )

            day = 6
            day(
                getDayString(day),
                Companion::memoryReallocationPart1,
                Companion::memoryReallocationPart2,
                daysSelected,
                args
            )

            day = 7
            day(
                getDayString(day),
                Companion::recursiveCircusPart1,
                Companion::recursiveCircusPart2,
                daysSelected,
                args
            )

            day = 8
            day(
                getDayString(day),
                Companion::iHeardYouLikeRegistersPart1,
                Companion::iHeardYouLikeRegistersPart2,
                daysSelected,
                args
            )

            day = 9
            day(
                getDayString(day),
                Companion::streamProcessingPart1,
                Companion::streamProcessingPart2,
                daysSelected,
                args
            )

            day = 10
            day(
                getDayString(day),
                Companion::knotHashPart1,
                Companion::knotHashPart2,
                daysSelected,
                args
            )

            day = 11
            day(
                getDayString(day),
                HexEd(getFileName(day))::solvePart1,
                HexEd(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 12
            day(
                getDayString(day),
                DigitalPlumber(getFileName(day))::solvePart1,
                DigitalPlumber(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 13
            day(
                getDayString(day),
                PacketScanners(getFileName(day))::solvePart1,
                PacketScanners(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 14
            day(
                getDayString(day),
                Companion::diskDefragmentationPart1,
                Companion::diskDefragmentationPart2,
                daysSelected,
                args
            )

            day = 15
            day(
                getDayString(day),
                DuelingGenerators(getFileName(day))::solvePart1,
                DuelingGenerators(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 16
            day(
                getDayString(day),
                Companion::permutationPromenadePart1,
                Companion::permutationPromenadePart2,
                daysSelected,
                args
            )

            day = 17
            day(
                getDayString(day),
                SpinLock(getFileName(day))::solvePart1,
                SpinLock(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 18
            day(
                getDayString(day),
                Duet(getFileName(day))::solvePart1,
                Duet(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 19
            day(
                getDayString(day),
                ASeriesOfTubes(getFileName(day))::solvePart1,
                ASeriesOfTubes(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 20
            day(
                getDayString(day),
                ParticleSwarm(getFileName(day))::solvePart1,
                ParticleSwarm(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 21
            day(
                getDayString(day),
                Companion::fractalArtPart1,
                FractalArt(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 22
            day(
                getDayString(day),
                Companion::sporificaVirusPart1,
                Companion::sporificaVirusPart2,
                daysSelected,
                args
            )

            day = 23
            day(
                getDayString(day),
                CoprocessorConflagration(getFileName(day))::solvePart1,
                CoprocessorConflagration(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 24
            day(
                getDayString(day),
                ElectromagneticMoat(getFileName(day))::solvePart1,
                ElectromagneticMoat(getFileName(day))::solvePart2,
                daysSelected,
                args
            )

            day = 25
            day(
                getDayString(day),
                TheHaltingProblem(getFileName(day))::solvePart1,
                Companion::merryXMAS,
                daysSelected,
                args
            )
        }

        private fun highEntropyPassphrasesPart1(): String {
            val filename = getFileName(4)

            return HighEntropyPassphrases.solvePart1(filename).toString()
        }

        private fun highEntropyPassphrasesPart2(): String {
            val filename = getFileName(4)

            return HighEntropyPassphrases.solvePart2(filename).toString()
        }

        private fun aMazeOfTwistyTrampolinesAllAlikePart1(): String {
            val filename = getFileName(5)

            return AMazeOfTwistyTrampolinesAllAlike.solvePart1(filename).toString()
        }

        private fun aMazeOfTwistyTrampolinesAllAlikePart2(): String {
            val filename = getFileName(5)

            return AMazeOfTwistyTrampolinesAllAlike.solvePart2(filename).toString()
        }

        private fun memoryReallocationPart1(): String {
            val filename = getFileName(6)

            return MemoryReallocation.solvePart1(filename).toString()
        }

        private fun memoryReallocationPart2(): String {
            val filename = getFileName(6)

            return MemoryReallocation.solvePart2(filename).toString()
        }

        private fun recursiveCircusPart1(): String {
            val filename = getFileName(7)

            return RecursiveCircus.solvePart1(filename).toString()
        }

        private fun recursiveCircusPart2(): String {
            val filename = getFileName(7)

            return RecursiveCircus.solvePart2(filename).toString()
        }

        private fun iHeardYouLikeRegistersPart1(): String {
            val filename = getFileName(8)

            return IHeardYouLikeRegisters.solvePart1(filename).toString()
        }

        private fun iHeardYouLikeRegistersPart2(): String {
            val filename = getFileName(8)

            return IHeardYouLikeRegisters.solvePart2(filename).toString()
        }

        private fun streamProcessingPart1(): String {
            val filename = getFileName(9)

            return StreamProcessing.solvePart1(filename).toString()
        }

        private fun streamProcessingPart2(): String {
            val filename = getFileName(9)

            return StreamProcessing.solvePart2(filename).toString()
        }

        private fun knotHashPart1(): String {
            val filename = getFileName(10)

            return KnotHash.solvePart1(filename, 256).toString()
        }

        private fun knotHashPart2(): String {
            val filename = getFileName(10)

            return KnotHash.solvePart2(filename).toString()
        }

        private fun diskDefragmentationPart1(): String {
            val filename = getFileName(14)

            return DiskDefragmentation.solvePart1(filename).toString()
        }

        private fun diskDefragmentationPart2(): String {
            val filename = getFileName(14)

            return DiskDefragmentation.solvePart2(filename).toString()
        }

        private fun permutationPromenadePart1(): String {
            val filename = getFileName(16)
            val day16initialWord = "abcdefghijklmnop";

            return PermutationPromenade.solvePart1(filename, day16initialWord).toString()
        }

        private fun permutationPromenadePart2(): String {
            val filename = getFileName(16)
            val day16initialWord = "abcdefghijklmnop";
            val day16iterations = 1000000000;

            return PermutationPromenade.solvePart2(filename, day16initialWord, day16iterations).toString()
        }

        private fun fractalArtPart1(): String {
            val filename = getFileName(21)

            return FractalArt.solvePart1(filename, 5).toString()
        }

        private fun sporificaVirusPart1(): String {
            val filename = getFileName(22)

            return SporificaVirus.solvePart1(filename, 10000).toString()
        }

        private fun sporificaVirusPart2(): String {
            val filename = getFileName(22)

            return SporificaVirus.solvePart2(filename, 10000000).toString()
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
            val year = 2017
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
