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
    fun testProcessPart2(filename: String, expectedResult: Long) =
        RaceCondition(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 1, 64),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 2, 40),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 3, 38),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 4, 36),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 5, 20),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 8, 12),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 10, 10),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 14, 8),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 16, 6),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 30, 4),
                Arguments.of("de/havox_design/aoc2024/day20/day20part1sample.txt", 44, 2)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day20/day20part2sample.txt", 0L)
            )
    }
}
