package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class JetTest {
    @ParameterizedTest
    @MethodSource("getDataForTestJets")
    fun testJets(code: String, expectedJet: Jet) =
        Jet.getJetForCode(code).shouldBe(expectedJet)

    companion object {
        @JvmStatic
        private fun getDataForTestJets(): Stream<Arguments> =
            Stream.of(
                Arguments.of("<", Jet.LEFT),
                Arguments.of(">", Jet.RIGHT),
                Arguments.of("v", Jet.DOWN),
                Arguments.of("foo", Jet.UNKNOWN),
                Arguments.of("bar", Jet.UNKNOWN),
                Arguments.of(".", Jet.UNKNOWN)
            )
    }
}
