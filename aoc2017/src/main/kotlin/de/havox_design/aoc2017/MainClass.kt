package de.havox_design.aoc2017

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
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

class MainClass: AocMainClassHelper {

    override fun getYear(): Int = 2017

    override fun processYear(args: Array<String>) {
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
            HighEntropyPassphrases(getFileName(day))::processPart1,
            HighEntropyPassphrases(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            AMazeOfTwistyTrampolinesAllAlike(getFileName(day))::processPart1,
            AMazeOfTwistyTrampolinesAllAlike(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            MemoryReallocation(getFileName(day))::processPart1,
            MemoryReallocation(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            RecursiveCircus(getFileName(day))::processPart1,
            RecursiveCircus(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            IHeardYouLikeRegisters(getFileName(day))::processPart1,
            IHeardYouLikeRegisters(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            StreamProcessing(getFileName(day))::processPart1,
            StreamProcessing(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            KnotHash(getFileName(day))::processPart1,
            KnotHash(getFileName(day))::processPart2,
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
            DiskDefragmentation(getFileName(day))::processPart1,
            DiskDefragmentation(getFileName(day))::processPart2,
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
            this::permutationPromenadePart1,
            this::permutationPromenadePart2,
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
            this::fractalArtPart1,
            FractalArt(getFileName(day))::solvePart2,
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            this::sporificaVirusPart1,
            this::sporificaVirusPart2,
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
            this::merryXMAS,
            daysSelected,
            args
        )
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val instance = MainClass()
            instance.processYear(args)
        }
    }
}
