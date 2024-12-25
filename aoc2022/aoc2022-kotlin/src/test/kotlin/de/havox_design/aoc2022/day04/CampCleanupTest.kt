package de.havox_design.aoc2022.day04

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CampCleanupTest {
    @ParameterizedTest
    @MethodSource("getDataForTestFindAssignmentPairsWithOneAssignmentContainingTheOther")
    fun testFindAssignmentPairsWithOneAssignmentContainingTheOther(
        filename: String,
        expectedNumberOfContainedAssignments: Int,
        expectedNumberOfOverlappingAssignments: Int
    ) =
        assertAll(
            {
                CampCleanup(filename).findAssignmentPairsWithOneAssignmentContainingTheOther()
                    .shouldBe(expectedNumberOfContainedAssignments)
            },
            {
                CampCleanup(filename).findAssignmentPairsWithOneAssignmentOverlappingTheOther()
                    .shouldBe(expectedNumberOfOverlappingAssignments)
            }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestFindAssignmentPairsWithOneAssignmentContainingTheOther(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow1.txt", 0, 0),
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow2.txt", 0, 0),
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow3.txt", 0, 1),
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow4.txt", 1, 1),
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow5.txt", 1, 1),
                Arguments.of("de/havox_design/aoc2022/day04/day04SampleRow6.txt", 0, 1),
                Arguments.of("de/havox_design/aoc2022/day04/day04Sample.txt", 2, 4)
            )
    }
}