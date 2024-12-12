package de.havox_design.aoc2024.day12

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GardenGroupsTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        GardenGroups(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        GardenGroups(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day12/day12part1sample1.txt", 140),
                Arguments.of("de/havox_design/aoc2024/day12/day12part1sample2.txt", 772),
                Arguments.of("de/havox_design/aoc2024/day12/day12part1sample3.txt", 1930)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2024/day12/day12part2sample1.txt", 80),
                Arguments.of("de/havox_design/aoc2024/day12/day12part2sample2.txt", 436),
                Arguments.of("de/havox_design/aoc2024/day12/day12part2sample3.txt", 236),
                Arguments.of("de/havox_design/aoc2024/day12/day12part2sample4.txt", 368),
                Arguments.of("de/havox_design/aoc2024/day12/day12part2sample5.txt", 1206)
            )
    }
}
