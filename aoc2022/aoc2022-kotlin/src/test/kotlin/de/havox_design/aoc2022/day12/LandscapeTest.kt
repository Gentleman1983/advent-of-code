package de.havox_design.aoc2022.day12

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LandscapeTest {
    @ParameterizedTest
    @MethodSource("getDataForTestInitLandscape")
    fun testInitLandscape(data: List<String>, expectedMapData: Map<Position, Field>) =
        Landscape(data).map.shouldBe(expectedMapData)

    companion object {
        @JvmStatic
        private fun getDataForTestInitLandscape(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "Sabqponm",
                        "abcryxxl",
                        "accszExk",
                        "acctuvwj",
                        "abdefghi"
                    ),
                    mapOf(
                        Pair(Position(0, 0), Field("S")),
                        Pair(Position(1, 0), Field("a")),
                        Pair(Position(2, 0),Field( "b")),
                        Pair(Position(3, 0), Field("q")),
                        Pair(Position(4, 0), Field("p")),
                        Pair(Position(5, 0), Field("o")),
                        Pair(Position(6, 0), Field("n")),
                        Pair(Position(7, 0), Field("m")),
                        Pair(Position(0, 1), Field("a")),
                        Pair(Position(1, 1), Field("b")),
                        Pair(Position(2, 1), Field("c")),
                        Pair(Position(3, 1), Field("r")),
                        Pair(Position(4, 1), Field("y")),
                        Pair(Position(5, 1), Field("x")),
                        Pair(Position(6, 1), Field("x")),
                        Pair(Position(7, 1), Field("l")),
                        Pair(Position(0, 2), Field("a")),
                        Pair(Position(1, 2), Field("c")),
                        Pair(Position(2, 2), Field("c")),
                        Pair(Position(3, 2), Field("s")),
                        Pair(Position(4, 2), Field("z")),
                        Pair(Position(5, 2), Field("E")),
                        Pair(Position(6, 2), Field("x")),
                        Pair(Position(7, 2), Field("k")),
                        Pair(Position(0, 3), Field("a")),
                        Pair(Position(1, 3), Field("c")),
                        Pair(Position(2, 3), Field("c")),
                        Pair(Position(3, 3), Field("t")),
                        Pair(Position(4, 3), Field("u")),
                        Pair(Position(5, 3), Field("v")),
                        Pair(Position(6, 3), Field("w")),
                        Pair(Position(7, 3), Field("j")),
                        Pair(Position(0, 4), Field("a")),
                        Pair(Position(1, 4), Field("b")),
                        Pair(Position(2, 4), Field("d")),
                        Pair(Position(3, 4), Field("e")),
                        Pair(Position(4, 4), Field("f")),
                        Pair(Position(5, 4), Field("g")),
                        Pair(Position(6, 4), Field("h")),
                        Pair(Position(7, 4), Field("i"))
                    )
                )
            )
    }
}
