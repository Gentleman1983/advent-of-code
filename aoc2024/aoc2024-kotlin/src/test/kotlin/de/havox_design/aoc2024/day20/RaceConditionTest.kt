package de.havox_design.aoc2024.day20

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RaceConditionTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int, minimumCostSaving: Int) =
        RaceCondition(filename).processPart1(minimumCostSaving).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int, minimumCostSaving: Int) =
        RaceCondition(filename).processPart2(minimumCostSaving).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 1, 64),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 2, 40),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 3, 38),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 4, 36),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 5, 20),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 8, 12),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 10, 10),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 14, 8),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 16, 6),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 30, 4),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 44, 2)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 285, 50),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 253, 52),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 222, 54),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 193, 56),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 154, 58),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 129, 60),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 106, 62),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 86, 64),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 67, 66),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 55, 68),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 41, 70),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 29, 72),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 7, 74),
                Arguments.of("de/havox_design/aoc2024/day20/day20sample.txt", 3, 76)
            )
    }
}
