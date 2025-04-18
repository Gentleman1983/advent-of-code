package de.havox_design.aoc2020.day02

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PasswordPhilosophyTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        PasswordPhilosophy(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        PasswordPhilosophy(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day02/day02sample1.txt", 2),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample2.txt", 1),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample3.txt", 0),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample4.txt", 1)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2020/day02/day02sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample2.txt", 1),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample3.txt", 0),
                Arguments.of("de/havox_design/aoc2020/day02/day02sample4.txt", 0)
            )
    }
}
