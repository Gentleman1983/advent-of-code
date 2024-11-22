package de.havox_design.aoc2017.day04

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day04Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        HighEntropyPassphrases(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        HighEntropyPassphrases(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day04/day04Part1sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part1sample2.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part1sample3.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part1sample4.txt", 2)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample2.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample3.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample4.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample5.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day04/day04Part2sample6.txt", 3)
            )
    }
}
