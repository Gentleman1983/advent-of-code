package de.havox_design.aoc2022.day04

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AssignmentPairTest {
    @ParameterizedTest
    @MethodSource("getDataForTestAssignmentPair")
    fun testAssignmentPair(
        row: String,
        expectedLeftAssignment: Assignment,
        expectedRightAssignment: Assignment,
        expectedOneAssignmentContainsTheOther: Boolean,
        expectedOneAssignmentOverlapsTheOther: Boolean
    ) {
        val objectUnderTest: AssignmentPair = AssignmentPair.processInputRow(row)

        assertAll(
            { objectUnderTest.leftAssignment.shouldBe(expectedLeftAssignment) },
            { objectUnderTest.rightAssignment.shouldBe(expectedRightAssignment) },
            { objectUnderTest.oneAssignmentContainsTheOther().shouldBe(expectedOneAssignmentContainsTheOther) },
            { objectUnderTest.oneAssignmentOverlapsTheOther().shouldBe(expectedOneAssignmentOverlapsTheOther) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestAssignmentPair(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "2-4,6-8",
                    Assignment.processSectionString("2-4"),
                    Assignment.processSectionString("6-8"),
                    false,
                    false
                ),
                Arguments.of(
                    "2-3,4-5",
                    Assignment.processSectionString("2-3"),
                    Assignment.processSectionString("4-5"),
                    false,
                    false
                ),
                Arguments.of(
                    "5-7,7-9",
                    Assignment.processSectionString("5-7"),
                    Assignment.processSectionString("7-9"),
                    false,
                    true
                ),
                Arguments.of(
                    "2-8,3-7",
                    Assignment.processSectionString("2-8"),
                    Assignment.processSectionString("3-7"),
                    true,
                    true
                ),
                Arguments.of(
                    "6-6,4-6",
                    Assignment.processSectionString("6-6"),
                    Assignment.processSectionString("4-6"),
                    true,
                    true
                ),
                Arguments.of(
                    "2-6,4-8",
                    Assignment.processSectionString("2-6"),
                    Assignment.processSectionString("4-8"),
                    false,
                    true
                )
            )
    }
}
