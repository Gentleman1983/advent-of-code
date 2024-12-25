package de.havox_design.aoc2019.day18

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ManyWorldsInterpretationPart2Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        ManyWorldsInterpretationPart2(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2019/day18/day18part2sample1.txt", 8),
                Arguments.of("de/havox_design/aoc2019/day18/day18part2sample2.txt", 24),
                Arguments.of("de/havox_design/aoc2019/day18/day18part2sample3.txt", 32),
                Arguments.of("de/havox_design/aoc2019/day18/day18part2sample4.txt", 72)
            )
    }
}
