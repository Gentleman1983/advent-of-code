package de.havox_design.aoc2020.day05

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day05Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        BinaryBoarding(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        BinaryBoarding(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample1.txt", 820L),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample2.txt", 357L),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample3.txt", 567L),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample4.txt", 119L),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample5.txt", 820L)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day05/day05part2sample.txt", 0L)
            )
    }
}
