package de.havox_design.aoc2018.day10

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test {

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: List<String>) =
        TheStarsAlign(filename).processTask1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Int) =
        TheStarsAlign(filename).processTask2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2018/day10/day10Sample.txt",
                    listOf(
                        "#...#..###",
                        "#...#...#.",
                        "#...#...#.",
                        "#####...#.",
                        "#...#...#.",
                        "#...#...#.",
                        "#...#...#.",
                        "#...#..###"
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2018/day10/day10Sample.txt", 0)
            )
    }
}
