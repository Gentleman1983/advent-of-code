package de.havox_design.aoc2023.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        PipeMaze(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        PipeMaze(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("day10Part1sample1.txt", 4L),
                Arguments.of("day10Part1sample2.txt", 8L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("day10Part2sample1.txt", 4L),
                Arguments.of("day10Part2sample2.txt", 4L),
                Arguments.of("day10Part2sample3.txt", 8L),
                Arguments.of("day10Part2sample4.txt", 10L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
