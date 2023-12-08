package de.havox_design.aoc2016.day25

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day25Test {
    @Test
    fun testMainClass() {
        MainClass.Companion.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        Day25(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("part2sample1.txt", 0L)
            )
    }
}

private fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)
