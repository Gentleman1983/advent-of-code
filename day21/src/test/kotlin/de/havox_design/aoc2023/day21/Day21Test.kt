package de.havox_design.aoc2023.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day21Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        Day21(filename).solvePart1(6).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long, steps: Int) =
        Day21(filename).solvePart2(steps).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 16L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 16L, 6),
                Arguments.of("sample.txt", 50L, 10),
                Arguments.of("sample.txt", 1594L, 50),
                Arguments.of("sample.txt", 6536L, 100),
                Arguments.of("sample.txt", 167004L, 500),
                Arguments.of("sample.txt", 668697L, 1000),
                Arguments.of("sample.txt", 16733044L, 5000)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
