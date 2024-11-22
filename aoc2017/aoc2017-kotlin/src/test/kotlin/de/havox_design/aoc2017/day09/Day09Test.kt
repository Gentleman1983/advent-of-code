package de.havox_design.aoc2017.day09

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day09Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        StreamProcessing(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        StreamProcessing(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample2.txt", 6),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample3.txt", 5),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample4.txt", 16),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample5.txt", 1),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample6.txt", 9),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample7.txt", 9),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part1sample8.txt", 3)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample1.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample2.txt", 17),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample3.txt", 3),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample4.txt", 2),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample5.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample6.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day09/day09Part2sample7.txt", 10)
            )
    }
}
