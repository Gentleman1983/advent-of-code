package de.havox_design.aoc2023.day05

import de.havox_design.aoc2023.MainClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day05Test {
    @Disabled("Long computation, 9min 15secs on Ryzen 7 5700G")
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("day05"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        IfYouGiveASeedAFertilizer(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        IfYouGiveASeedAFertilizer(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day05/day05Sample.txt", 35L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day05/day05Sample.txt", 46L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = assertEquals(expectation, this)
