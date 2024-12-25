package de.havox_design.aoc2022.day09

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DirectionTest {
    @ParameterizedTest
    @MethodSource("getDataForFindDirectionBySymbol")
    fun findDirectionBySymbol(symbol: String, expectedDirection: Direction) =
        Direction.findDirectionBySymbol(symbol).shouldBe(expectedDirection)

    @ParameterizedTest
    @MethodSource("getDataForFindDirectionByMovement")
    fun findDirectionByMovement(row: Int, col: Int, expectedDirection: Direction) =
        Direction.findDirectionByMovement(row, col).shouldBe(expectedDirection)

    companion object {
        @JvmStatic
        private fun getDataForFindDirectionBySymbol(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Direction.UP_LEFT.symbol, Direction.UP_LEFT),
                Arguments.of(Direction.UP.symbol, Direction.UP),
                Arguments.of(Direction.UP_RIGHT.symbol, Direction.UP_RIGHT),
                Arguments.of(Direction.RIGHT.symbol, Direction.RIGHT),
                Arguments.of(Direction.DOWN_RIGHT.symbol, Direction.DOWN_RIGHT),
                Arguments.of(Direction.DOWN.symbol, Direction.DOWN),
                Arguments.of(Direction.DOWN_LEFT.symbol, Direction.DOWN_LEFT),
                Arguments.of(Direction.LEFT.symbol, Direction.LEFT),
                Arguments.of(Direction.UNKNOWN.symbol, Direction.UNKNOWN),
                Arguments.of("foo", Direction.UNKNOWN),
                Arguments.of("bar", Direction.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForFindDirectionByMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Direction.UP_LEFT.modRow, Direction.UP_LEFT.modCol, Direction.UP_LEFT),
                Arguments.of(Direction.UP.modRow, Direction.UP.modCol, Direction.UP),
                Arguments.of(Direction.UP_RIGHT.modRow, Direction.UP_RIGHT.modCol, Direction.UP_RIGHT),
                Arguments.of(Direction.RIGHT.modRow, Direction.RIGHT.modCol, Direction.RIGHT),
                Arguments.of(Direction.DOWN_RIGHT.modRow, Direction.DOWN_RIGHT.modCol, Direction.DOWN_RIGHT),
                Arguments.of(Direction.DOWN.modRow, Direction.DOWN.modCol, Direction.DOWN),
                Arguments.of(Direction.DOWN_LEFT.modRow, Direction.DOWN_LEFT.modCol, Direction.DOWN_LEFT),
                Arguments.of(Direction.LEFT.modRow, Direction.LEFT.modCol, Direction.LEFT),
                Arguments.of(Direction.UNKNOWN.modRow, Direction.UNKNOWN.modCol, Direction.UNKNOWN),
                Arguments.of(47, 11, Direction.UNKNOWN),
                Arguments.of(-8, 15, Direction.UNKNOWN)
            )
    }
}
