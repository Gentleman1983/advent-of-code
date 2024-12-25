package de.havox_design.aoc2022.day12

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FieldTest {

    @ParameterizedTest
    @MethodSource("getDataForTestFieldReachable")
    fun testFieldReachable(source: Field, target: Field, expectedToBeVisited: Boolean) =
        source.canVisitField(target).shouldBe(expectedToBeVisited)

    companion object {
        @JvmStatic
        private fun getDataForTestFieldReachable(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Field("a"), Field("b"), true),
                Arguments.of(Field("S"), Field("a"), true),
                Arguments.of(Field("S"), Field("b"), true),
                Arguments.of(Field("S"), Field("c"), false),
                Arguments.of(Field("x"), Field("E"), false),
                Arguments.of(Field("y"), Field("E"), true),
                Arguments.of(Field("z"), Field("E"), true),
                Arguments.of(Field("o"), Field("o"), true),
                Arguments.of(Field("E"), Field("S"), true)
            )
    }
}
