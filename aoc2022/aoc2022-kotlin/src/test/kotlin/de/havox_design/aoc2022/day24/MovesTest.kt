package de.havox_design.aoc2022.day24

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MovesTest {
    @ParameterizedTest
    @MethodSource("getDataForTestMovesDestinations")
    fun testMovesDestinations(move: Moves, expectedResult: Position2d<Int>) =
        move.getDirection(Position2d(0, 0)).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestMovesDestinations(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Moves.NORTH, Position2d(0, -1)),
                Arguments.of(Moves.EAST, Position2d(1, 0)),
                Arguments.of(Moves.SOUTH, Position2d(0, 1)),
                Arguments.of(Moves.WEST, Position2d(-1, 0)),
                Arguments.of(Moves.WAIT, Position2d(0, 0))
            )
    }
}
