package de.havox_design.aoc2019

import de.havox_design.aoc.utils.kotlin.helpers.AocMainClassHelper
import de.havox_design.aoc2019.day01.TheTyrannyOfTheRocketEquation
import de.havox_design.aoc2019.day02.ProgramAlarm1202
import de.havox_design.aoc2019.day03.CrossedWires
import de.havox_design.aoc2019.day04.SecureContainer
import de.havox_design.aoc2019.day05.SunnyWithAChanceOfAsteroids
import de.havox_design.aoc2019.day06.UniversalOrbitMap
import de.havox_design.aoc2019.day07.AmplificationCircuit
import de.havox_design.aoc2019.day08.SpaceImageFormat
import de.havox_design.aoc2019.day09.SensorBoost
import de.havox_design.aoc2019.day10.MonitoringStation
import de.havox_design.aoc2019.day11.SpacePolice
import de.havox_design.aoc2019.day12.TheNBodyProblem
import de.havox_design.aoc2019.day13.CarePackage
import de.havox_design.aoc2019.day13.CarePackagePart2
import de.havox_design.aoc2019.day14.SpaceStoichiometry
import de.havox_design.aoc2019.day15.OxygenSystem
import de.havox_design.aoc2019.day16.FlawedFrequencyTransmission
import de.havox_design.aoc2019.day17.SetAndForget
import de.havox_design.aoc2019.day18.ManyWorldsInterpretation
import de.havox_design.aoc2019.day18.ManyWorldsInterpretationPart2
import de.havox_design.aoc2019.day19.TractorBeam
import de.havox_design.aoc2019.day20.DonutMaze
import de.havox_design.aoc2019.day21.SpringdroidAdventure
import de.havox_design.aoc2019.day22.SlamShuffle
import de.havox_design.aoc2019.day23.CategorySix
import de.havox_design.aoc2019.day24.PlanetOfDiscord
import de.havox_design.aoc2019.day25.Cryostasis

class MainClass: AocMainClassHelper {
    override fun getYear(): Int = 2019

    override fun processYear(args: Array<String>) {
        val daysSelected = args
            .any { argument -> argument.startsWith("day") }

        var day = 1
        day(
            getDayString(day),
            TheTyrannyOfTheRocketEquation(getFileName(day))::processTask1,
            TheTyrannyOfTheRocketEquation(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 2
        day(
            getDayString(day),
            ProgramAlarm1202(getFileName(day))::processTask1,
            ProgramAlarm1202(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 3
        day(
            getDayString(day),
            CrossedWires(getFileName(day))::processTask1,
            CrossedWires(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 4
        day(
            getDayString(day),
            SecureContainer(getFileName(day))::processTask1,
            SecureContainer(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 5
        day(
            getDayString(day),
            SunnyWithAChanceOfAsteroids(getFileName(day))::processTask1,
            SunnyWithAChanceOfAsteroids(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 6
        day(
            getDayString(day),
            UniversalOrbitMap(getFileName(day))::processTask1,
            UniversalOrbitMap(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 7
        day(
            getDayString(day),
            AmplificationCircuit(getFileName(day))::processTask1,
            AmplificationCircuit(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 8
        day(
            getDayString(day),
            SpaceImageFormat(getFileName(day))::processTask1,
            SpaceImageFormat(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 9
        day(
            getDayString(day),
            SensorBoost(getFileName(day))::processTask1,
            SensorBoost(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 10
        day(
            getDayString(day),
            MonitoringStation(getFileName(day))::processTask1,
            MonitoringStation(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 11
        day(
            getDayString(day),
            SpacePolice(getFileName(day))::processTask1,
            SpacePolice(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 12
        day(
            getDayString(day),
            TheNBodyProblem(getFileName(day))::processTask1,
            TheNBodyProblem(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 13
        day(
            getDayString(day),
            CarePackage(getFileName(day))::processTask1,
            CarePackagePart2(getFileName(day))::processPart2,
            daysSelected,
            args
        )

        day = 14
        day(
            getDayString(day),
            SpaceStoichiometry(getFileName(day))::processTask1,
            SpaceStoichiometry(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 15
        day(
            getDayString(day),
            OxygenSystem(getFileName(day))::processTask1,
            OxygenSystem(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 16
        day(
            getDayString(day),
            FlawedFrequencyTransmission(getFileName(day))::processTask1,
            FlawedFrequencyTransmission(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 17
        day(
            getDayString(day),
            SetAndForget(getFileName(day))::processTask1,
            SetAndForget(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 18
        day(
            getDayString(day),
            ManyWorldsInterpretation(getFileName(day))::processTask1,
            ManyWorldsInterpretationPart2(getFileName(day).replace(".txt","part2.txt"))::processPart2,
            daysSelected,
            args
        )

        day = 19
        day(
            getDayString(day),
            TractorBeam(getFileName(day))::processTask1,
            TractorBeam(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 20
        day(
            getDayString(day),
            DonutMaze(getFileName(day))::processTask1,
            DonutMaze(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 21
        day(
            getDayString(day),
            SpringdroidAdventure(getFileName(day))::processTask1,
            SpringdroidAdventure(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 22
        day(
            getDayString(day),
            SlamShuffle(getFileName(day))::processTask1,
            SlamShuffle(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 23
        day(
            getDayString(day),
            CategorySix(getFileName(day))::processTask1,
            CategorySix(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 24
        day(
            getDayString(day),
            PlanetOfDiscord(getFileName(day))::processTask1,
            PlanetOfDiscord(getFileName(day))::processTask2,
            daysSelected,
            args
        )

        day = 25
        day(
            getDayString(day),
            Cryostasis(getFileName(day))::processPart1,
            Cryostasis(getFileName(day))::processPart2,
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
