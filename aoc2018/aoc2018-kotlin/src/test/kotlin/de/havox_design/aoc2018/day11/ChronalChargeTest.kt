package de.havox_design.aoc2018.day11

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ChronalChargeTest {

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: String) =
        ChronalCharge(filename).processTask1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: String) =
        ChronalCharge(filename).processTask2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2018/day11/day11part1sample.txt", "21,61")
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2018/day11/day11part2sample1.txt", "90,269,16"),
                Arguments.of("de/havox_design/aoc2018/day11/day11part2sample2.txt", "232,251,12")
            )
    }
}
