package de.havox_design.aoc2023.day12

import de.havox_design.aoc2023.MainClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day12Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("day12"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        HotSprings(filename).solvePart1().shouldBe(expectedResult)

    @Disabled("takes much toooooooooooooooooo long...")
    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        HotSprings(filename).solvePart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testQuicksolvePart2(filename: String, expectedResult: Long) =
        HotSprings(filename).quicksolvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day12/day12Sample.txt", 21L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day12/day12Sample.txt", 525152L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
