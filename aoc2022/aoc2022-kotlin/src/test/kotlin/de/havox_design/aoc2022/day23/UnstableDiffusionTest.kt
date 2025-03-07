package de.havox_design.aoc2022.day23

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UnstableDiffusionTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, endInRound:Int, expectedResult: Int) =
        UnstableDiffusion(filename).processPart1(endInRound).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        UnstableDiffusion(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day23/day23SampleSmall.txt", 10, 25),
                Arguments.of("de/havox_design/aoc2022/day23/day23SampleLarge.txt", 10, 110)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day23/day23SampleSmall.txt", 4),
                Arguments.of("de/havox_design/aoc2022/day23/day23SampleLarge.txt", 20)
            )
    }
}
