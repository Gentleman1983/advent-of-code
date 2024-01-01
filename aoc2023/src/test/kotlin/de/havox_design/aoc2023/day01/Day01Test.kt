package de.havox_design.aoc2023.day01

import de.havox_design.aoc2023.MainClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day01Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("day01"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        Trebuchet(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        Trebuchet(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day01/day01Part1sample1.txt", 12L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part1sample2.txt", 38L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part1sample3.txt", 15L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part1sample4.txt", 77L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part1sample5.txt", 142L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample1.txt", 29L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample2.txt", 83L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample3.txt", 13L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample4.txt", 24L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample5.txt", 42L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample6.txt", 14L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample7.txt", 76L),
                Arguments.of("de/havox_design/aoc2023/day01/day01Part2sample8.txt", 281L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
