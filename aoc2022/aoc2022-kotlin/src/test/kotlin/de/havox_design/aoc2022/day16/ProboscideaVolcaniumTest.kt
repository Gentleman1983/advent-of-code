package de.havox_design.aoc2022.day16

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProboscideaVolcaniumTest {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        ProboscideaVolcanium(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        ProboscideaVolcanium(filename).processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestImportValves")
    fun testImportValves(filename: String, expectedValves: Map<String, Valve>) =
        ProboscideaVolcanium(filename).valves.shouldBe(expectedValves)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day16/day16Sample.txt", 1651)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day16/day16Sample.txt", 1707)
            )

        @JvmStatic
        private fun getDataForTestImportValves(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day16/day16Sample.txt",
                    mapOf(
                        Pair("AA", Valve("AA", 0, listOf("DD", "II", "BB"))),
                        Pair("BB", Valve("BB", 13, listOf("CC", "AA"))),
                        Pair("CC", Valve("CC", 2, listOf("DD", "BB"))),
                        Pair("DD", Valve("DD", 20, listOf("CC", "AA", "EE"))),
                        Pair("EE", Valve("EE", 3, listOf("FF", "DD"))),
                        Pair("FF", Valve("FF", 0, listOf("EE", "GG"))),
                        Pair("GG", Valve("GG", 0, listOf("FF", "HH"))),
                        Pair("HH", Valve("HH", 22, listOf("GG"))),
                        Pair("II", Valve("II", 0, listOf("AA", "JJ"))),
                        Pair("JJ", Valve("JJ", 21, listOf("II")))
                    )
                )
            )
    }
}
