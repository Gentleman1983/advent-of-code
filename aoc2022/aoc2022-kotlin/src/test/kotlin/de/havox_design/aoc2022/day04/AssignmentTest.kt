package de.havox_design.aoc2022.day04

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AssignmentTest {
    @ParameterizedTest
    @MethodSource("getDataForTestAssignment")
    fun testAssignment(
        sectionString: String,
        expectedLowerBond: Int,
        expectedUpperBond: Int,
        expectedSections: List<Int>
    ) {
        val objectUnderTest: Assignment = Assignment.processSectionString(sectionString)

        assertAll(
            { objectUnderTest.lowerSection.shouldBe(expectedLowerBond) },
            { objectUnderTest.upperSection.shouldBe(expectedUpperBond) },
            { objectUnderTest.getSections().shouldContainAll(expectedSections) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestAssignment(): Stream<Arguments> =
            Stream.of(
                Arguments.of("2-4", 2, 4, listOf(2, 3, 4)),
                Arguments.of("2-3", 2, 3, listOf(2, 3)),
                Arguments.of("5-7", 5, 7, listOf(5, 6, 7)),
                Arguments.of("2-8", 2, 8, listOf(2, 3, 4, 5, 6, 7, 8)),
                Arguments.of("6-6", 6, 6, listOf(6)),
                Arguments.of("2-6", 2, 6, listOf(2, 3, 4, 5, 6)),
                Arguments.of("6-8", 6, 8, listOf(6, 7, 8)),
                Arguments.of("4-5", 4, 5, listOf(4, 5)),
                Arguments.of("7-9", 7, 9, listOf(7, 8, 9)),
                Arguments.of("3-7", 3, 7, listOf(3, 4, 5, 6, 7)),
                Arguments.of("4-6", 4, 6, listOf(4, 5, 6)),
                Arguments.of("4-8", 4, 8, listOf(4, 5, 6, 7, 8)),
                Arguments.of("2-1", 1, 2, listOf(1, 2))
            )
    }
}