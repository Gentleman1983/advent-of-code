package de.havox_design.aoc2020.day05

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day05Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        BinaryBoarding(filename).processPart1().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample1.txt", 820),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample2.txt", 357),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample3.txt", 567),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample4.txt", 119),
                Arguments.of("de/havox_design/aoc2020/day05/day05part1sample5.txt", 820)
            )
    }
}
