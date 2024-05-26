package de.havox_design.aoc2021.day16

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day16Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        PacketDecoder(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        PacketDecoder(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample1.txt", 6),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample2.txt", 9),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample3.txt", 14),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample4.txt", 16),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample5.txt", 12),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample6.txt", 23),
                Arguments.of("de/havox_design/aoc2021/day16/day16part1sample7.txt", 31)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2021/day16/day16part2sample.txt", 0L)
            )
    }
}
