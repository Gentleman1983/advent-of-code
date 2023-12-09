package de.havox_design.aoc2023.day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day09Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        Day09(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        Day09(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample1.txt", 18L),
                Arguments.of("sample2.txt", 28L),
                Arguments.of("sample3.txt", 68L),
                Arguments.of("sample4.txt", 114L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample1.txt", 0L),
                Arguments.of("sample2.txt", 0L),
                Arguments.of("sample3.txt", 0L),
                Arguments.of("sample4.txt", 0L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
