package de.havox_design.aoc2015.day18

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day18Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int, steps: Int) =
        GIFMatrix(filename).processPart1(steps).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int, steps: Int) =
        GIFMatrix(filename).processPart2(steps).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 11, 1),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 8, 2),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 4, 3),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 4, 4)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 18, 1),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 18, 2),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 18, 3),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 14, 4),
                Arguments.of("de/havox_design/aoc2015/day18/day18Sample.txt", 17, 5)
            )
    }
}
