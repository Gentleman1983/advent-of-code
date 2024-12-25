package de.havox_design.aoc2022.day16

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ValveTest {
    @ParameterizedTest
    @MethodSource("getDataForTestCreateValves")
    fun testCreateValves(creationString: String, expectedValve: Valve) =
        Valve.from(creationString).shouldBe(expectedValve)

    companion object {
        @JvmStatic
        private fun getDataForTestCreateValves(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                    Valve("AA", 0, listOf("DD", "II", "BB"))
                ),
                Arguments.of(
                    "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                    Valve("BB", 13, listOf("CC", "AA"))
                ),
                Arguments.of(
                    "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                    Valve("CC", 2, listOf("DD", "BB"))
                ),
                Arguments.of(
                    "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                    Valve("DD", 20, listOf("CC", "AA", "EE"))
                ),
                Arguments.of(
                    "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                    Valve("EE", 3, listOf("FF", "DD"))
                ),
                Arguments.of(
                    "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                    Valve("FF", 0, listOf("EE", "GG"))
                ),
                Arguments.of(
                    "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                    Valve("GG", 0, listOf("FF", "HH"))
                ),
                Arguments.of(
                    "Valve HH has flow rate=22; tunnel leads to valve GG",
                    Valve("HH", 22, listOf("GG"))
                ),
                Arguments.of(
                    "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                    Valve("II", 0, listOf("AA", "JJ"))
                ),
                Arguments.of(
                    "Valve JJ has flow rate=21; tunnel leads to valve II",
                    Valve("JJ", 21, listOf("II"))
                )
            )
    }
}
