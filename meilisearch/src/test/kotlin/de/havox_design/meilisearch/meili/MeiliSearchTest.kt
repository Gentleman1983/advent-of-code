package de.havox_design.meilisearch.meili

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MeiliSearchTest {
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: String) =
        MeiliSearch(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        MeiliSearch(filename).processPart2(arrayOf("testing")).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, expectedResult: Map<String, String>) =
        MeiliSearch(filename).data.shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", "tommy")
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 21)
            )

        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    mapOf(
                        Pair("tamo", "RLRLR"),
                        Pair("loic", "RLLL"),
                        Pair("kero", "LRLR"),
                        Pair("luna", "LRRR"),
                        Pair("caro", "LRL"),
                        Pair("lena", "RLLR"),
                        Pair("thomas", "LRLL"),
                        Pair("tommy", "LLL"),
                        Pair("chayaline", "LRLL")
                    )
                )
            )
    }
}
