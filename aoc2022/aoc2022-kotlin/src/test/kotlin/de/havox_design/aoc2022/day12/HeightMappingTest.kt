package de.havox_design.aoc2022.day12

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HeightMappingTest {
    @ParameterizedTest
    @MethodSource("getDataForTestGetElevationBySymbol")
    fun testGetElevationBySymbol(one: String, other: String) =
        HeightMapping.getElevationBySymbol(one).shouldBe(HeightMapping.getElevationBySymbol(other))

    companion object {
        @JvmStatic
        private fun getDataForTestGetElevationBySymbol(): Stream<Arguments> =
            Stream.of(
                Arguments.of("a", "a"),
                Arguments.of("S", "a"),
                Arguments.of("a", "S"),
                Arguments.of("z", "z"),
                Arguments.of("z", "E"),
                Arguments.of("E", "z")
            )
    }
}
