package de.havox_design.aoc2020.day13

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ShuttleSearchTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        ShuttleSearch(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        ShuttleSearch(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day13/day13part1sample.txt", 295)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample1.txt", 1068781L),
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample2.txt", 3417L),
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample3.txt", 754018L),
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample4.txt", 779210L),
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample5.txt", 1261476L),
                Arguments.of("de/havox_design/aoc2020/day13/day13part2sample6.txt", 1202161486L)
            )
    }
}
