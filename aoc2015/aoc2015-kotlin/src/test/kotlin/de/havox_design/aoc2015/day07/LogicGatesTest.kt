package de.havox_design.aoc2015.day07

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LogicGatesTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        LogicGates(filename).processPart1().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample1.txt", 72),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample2.txt", 507),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample3.txt", 492),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample4.txt", 114),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample5.txt", 65412),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample6.txt", 65079),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample7.txt", 123),
                Arguments.of("de/havox_design/aoc2015/day07/day07Part1sample8.txt", 456)
            )
    }
}
