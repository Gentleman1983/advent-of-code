package de.havox_design.aoc2022.day09

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GridTest {
    @ParameterizedTest
    @MethodSource("getDataForTailMovement")
    fun testTailMovement(
        grid: Grid,
        headStartRow: Int,
        headStartCol: Int,
        tailStartRow: Int,
        tailStartCol: Int,
        direction: Direction,
        expectedTailRow: Int,
        expectedTailCol: Int
    ) {
        grid.visitPosition(headStartRow, headStartCol, Knot.HEAD)
        grid.visitPosition(tailStartRow, tailStartCol, Knot.TAIL)
        grid.move(headStartRow, headStartCol, direction)

        val headMoveToRow = headStartRow + direction.modRow
        val headMoveToCol = headStartCol + direction.modCol

        assertAll(
            { grid.getPosition(headMoveToRow, headMoveToCol).knot.shouldBe(Knot.HEAD) },
            { grid.getPosition(expectedTailRow, expectedTailCol).knot.shouldBe(Knot.TAIL) }
        )
    }

    @Test
    fun verifyEqualsContractOnGridClass() {
        EqualsVerifier.forClass(Grid.Companion::class.java).suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT).verify()
    }

    companion object {
        @JvmStatic
        private fun getDataForTailMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Grid(10, 10), 1, 2, 1, 1, Direction.RIGHT, 1, 2),
                Arguments.of(Grid(10, 10), 2, 1, 1, 1, Direction.DOWN, 2, 1),
                Arguments.of(Grid(10, 10), 2, 2, 3, 1, Direction.UP, 2, 2),
                Arguments.of(Grid(10, 10), 2, 2, 3, 1, Direction.RIGHT, 2, 2)
            )
    }
}

private fun Knot?.shouldBe(expectation: Knot?) = Assertions.assertEquals(expectation, this)
