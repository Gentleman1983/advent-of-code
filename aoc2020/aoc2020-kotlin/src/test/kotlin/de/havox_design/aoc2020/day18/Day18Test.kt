package de.havox_design.aoc2020.day18

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day18Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        OperationOrder(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        OperationOrder(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample1.txt", 71L),
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample2.txt", 51L),
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample3.txt", 26L),
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample4.txt", 437L),
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample5.txt", 12240L),
                Arguments.of("de/havox_design/aoc2020/day18/day18part1sample6.txt", 13632L)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day18/day18part2sample.txt", 0L)
            )
    }
}
