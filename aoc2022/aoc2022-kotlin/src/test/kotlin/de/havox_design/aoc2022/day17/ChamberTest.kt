package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ChamberTest {
    @ParameterizedTest
    @MethodSource("getDataForTestRockSpawning")
    fun testRockSpawning(expectedPosition: Position2d<Long>) =
        Chamber().getStartPositionForRock().shouldBe(expectedPosition)

    companion object {
        @JvmStatic
        private fun getDataForTestRockSpawning(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Position2d(2L, 3L))
            )
    }
}
