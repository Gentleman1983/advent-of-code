package de.havox_design.aoc2023.day11

import de.havox_design.aoc2023.MainClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day11Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("day11"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        CosmicExpansion(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long, expansion: Long) =
        CosmicExpansion(filename).solvePart2(expansion).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day11/day11Sample.txt", 374L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day11/day11Sample.txt", 1030L, 10L),
                Arguments.of("de/havox_design/aoc2023/day11/day11Sample.txt", 8410L, 100L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
