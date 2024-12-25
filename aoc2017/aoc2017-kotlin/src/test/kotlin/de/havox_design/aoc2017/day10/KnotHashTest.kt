package de.havox_design.aoc2017.day10

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class KnotHashTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int, size: Int) =
        KnotHash(filename).processPart1(size).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: String) =
        KnotHash(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day10/day10Part1sample.txt", 12, 5)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2017/day10/day10Part2sample1.txt", "a2582a3a0e66e6e86e3812dcb672a272"),
                Arguments.of("de/havox_design/aoc2017/day10/day10Part2sample2.txt", "33efeb34ea91902bb2f59c9920caa6cd"),
                Arguments.of("de/havox_design/aoc2017/day10/day10Part2sample3.txt", "3efbe78a8d82f29979031a4aa0b16a9d"),
                Arguments.of("de/havox_design/aoc2017/day10/day10Part2sample4.txt", "63960835bcdc130f0b66d7ff4f6a5a8e")
            )
    }
}
