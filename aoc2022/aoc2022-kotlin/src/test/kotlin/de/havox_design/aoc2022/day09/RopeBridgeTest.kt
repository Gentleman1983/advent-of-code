package de.havox_design.aoc2022.day09

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RopeBridgeTest {
    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    fun readFile(filename: String, expectedMoves: List<Move>) {
        val objectUnderTest = RopeBridge(filename)

        assertAll(
            { objectUnderTest.moves.shouldContainAll(expectedMoves) },
            { objectUnderTest.moves.shouldBe(expectedMoves) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPart1")
    fun testPart1(filename: String, expectedVisitedFields: Int) {
        RopeBridge(filename).processPart1().shouldBe(expectedVisitedFields)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPart2")
    fun testPart2(filename: String, expectedVisitedFields: Int) {
        RopeBridge(filename).processPart2().shouldBe(expectedVisitedFields)
    }

    companion object {
        @JvmStatic
        private fun getDataForReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day09/day09Sample1.txt",
                    listOf(
                        Move(Direction.RIGHT, 4),
                        Move(Direction.UP, 4),
                        Move(Direction.LEFT, 3),
                        Move(Direction.DOWN, 1),
                        Move(Direction.RIGHT, 4),
                        Move(Direction.DOWN, 1),
                        Move(Direction.LEFT, 5),
                        Move(Direction.RIGHT, 2)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day09/day09Sample1.txt", 13)
            )

        @JvmStatic
        private fun getDataForTestPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day09/day09Sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2022/day09/day09Sample2.txt", 36)
            )
    }
}
