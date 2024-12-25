package de.havox_design.aoc2022.day24

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BlizzardBasinTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        BlizzardBasin(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(
        filename: String,
        expectedResult1stWayToGoal: Int,
        expectedResultWayBackToStart: Int,
        expectedResult2ndWayToGoal: Int,
        expectedResultTotal: Int
    ) =
        assertAll(
            { BlizzardBasin(filename).processPart2().get1stWayToGoalResult().shouldBe(expectedResult1stWayToGoal) },
            { BlizzardBasin(filename).processPart2().getWayBackToStartResult().shouldBe(expectedResultWayBackToStart) },
            { BlizzardBasin(filename).processPart2().get2ndWayToGoalResult().shouldBe(expectedResult2ndWayToGoal) },
            { BlizzardBasin(filename).processPart2().getTotalWayResult().shouldBe(expectedResultTotal) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestStartAndEnd")
    fun testStartAndEnd(filename: String, expectedStart: Position2d<Int>, expectedEnd: Position2d<Int>) =
        assertAll(
            { BlizzardBasin(filename).getStart().shouldBe(expectedStart) },
            { BlizzardBasin(filename).getEnd().shouldBe(expectedEnd) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", 10),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", 18)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", 10, 10, 10, 30),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", 18, 23, 13, 54)
            )

        @JvmStatic
        private fun getDataForTestStartAndEnd(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleEasy.txt", Position2d(1, 0), Position2d(5, 6)),
                Arguments.of("de/havox_design/aoc2022/day24/day24SampleComplex.txt", Position2d(1, 0), Position2d(6, 5))
            )
    }
}

private fun Array<Int>.get1stWayToGoalResult(): Int = this[0]
private fun Array<Int>.getWayBackToStartResult(): Int = this[1]
private fun Array<Int>.get2ndWayToGoalResult(): Int = this[2]
private fun Array<Int>.getTotalWayResult(): Int = this[3]
