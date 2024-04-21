package de.havox_design.aoc2020.day15

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day15Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        RambunctiousRecitation(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        RambunctiousRecitation(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample1.txt", 436L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample2.txt", 1L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample3.txt", 10L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample4.txt", 27L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample5.txt", 78L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample6.txt", 438L),
                Arguments.of("de/havox_design/aoc2020/day15/day15part1sample7.txt", 1836L)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day15/day15part2sample.txt", 0L)
            )
    }
}
