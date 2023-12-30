package de.havox_design.aoc2023.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day01Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        Day01(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        Day01(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("part1sample1.txt", 12L),
                Arguments.of("part1sample2.txt", 38L),
                Arguments.of("part1sample3.txt", 15L),
                Arguments.of("part1sample4.txt", 77L),
                Arguments.of("part1sample5.txt", 142L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("part2sample1.txt", 29L),
                Arguments.of("part2sample2.txt", 83L),
                Arguments.of("part2sample3.txt", 13L),
                Arguments.of("part2sample4.txt", 24L),
                Arguments.of("part2sample5.txt", 42L),
                Arguments.of("part2sample6.txt", 14L),
                Arguments.of("part2sample7.txt", 76L),
                Arguments.of("part2sample8.txt", 281L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
