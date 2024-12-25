package de.havox_design.aoc2018.day18

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SettlersOfTheNorthPoleKotlinTest {

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Int, minutes: Int) =
        SettlersOfTheNorthPoleKotlin(filename).processTask1(minutes).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 459, 0),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 480, 1),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 550, 2),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 682, 3),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 845, 4),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1056, 5),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1122, 6),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1464, 7),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1674, 8),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1496, 9),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1147, 10)
            )
    }
}
