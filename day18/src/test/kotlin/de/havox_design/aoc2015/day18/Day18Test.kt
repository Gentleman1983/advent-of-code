package de.havox_design.aoc2015.day18

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day18Test {
    @Test
    fun testMainClass() =
        MainClass.Companion.main(arrayOf())

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int, steps: Int) =
        GIFMatrix(filename).processPart1(steps).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("part1sample.txt", 11, 1),
                Arguments.of("part1sample.txt", 8, 2),
                Arguments.of("part1sample.txt", 4, 3),
                Arguments.of("part1sample.txt", 4, 4)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
