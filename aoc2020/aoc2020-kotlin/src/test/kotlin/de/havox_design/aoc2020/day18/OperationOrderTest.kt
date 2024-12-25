package de.havox_design.aoc2020.day18

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OperationOrderTest {

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
                Arguments.of("de/havox_design/aoc2020/day18/day18sample1.txt", 71L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample2.txt", 51L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample3.txt", 26L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample4.txt", 437L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample5.txt", 12240L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample6.txt", 13632L)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day18/day18sample1.txt", 231L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample2.txt", 51L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample3.txt", 46L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample4.txt", 1445L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample5.txt", 669060L),
                Arguments.of("de/havox_design/aoc2020/day18/day18sample6.txt", 23340L)
            )
    }
}
