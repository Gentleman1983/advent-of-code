package de.havox_design.aoc2022.day14

import de.havox_design.aoc2022.MainClass
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day14Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("day14"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        RegolithReservoir(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        RegolithReservoir(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day14/day14Sample.txt", 24)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day14/day14Sample.txt", 93)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
