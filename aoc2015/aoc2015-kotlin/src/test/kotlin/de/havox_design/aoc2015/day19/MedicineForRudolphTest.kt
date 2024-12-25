package de.havox_design.aoc2015.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MedicineForRudolphTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        MedicineForRudolph(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        MedicineForRudolph(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2015/day19/day19Part1sample1.txt", 4),
                Arguments.of("de/havox_design/aoc2015/day19/day19Part1sample2.txt", 7),
                Arguments.of("de/havox_design/aoc2015/day19/day19Part1sample3.txt", 1)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2015/day19/day19Part2sample1.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day19/day19Part2sample2.txt", 6),
                Arguments.of("de/havox_design/aoc2015/day19/day19Part2sample3.txt", 2)
            )
    }
}
