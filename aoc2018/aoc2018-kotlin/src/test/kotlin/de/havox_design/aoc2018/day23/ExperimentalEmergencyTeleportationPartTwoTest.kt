package de.havox_design.aoc2018.day23

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ExperimentalEmergencyTeleportationPartTwoTest {

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Int) =
        ExperimentalEmergencyTeleportationPartTwo(filename).processTask2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2018/day23/day23part2sample.txt", 36)
            )
    }
}
