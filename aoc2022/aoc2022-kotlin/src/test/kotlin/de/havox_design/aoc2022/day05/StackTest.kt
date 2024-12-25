package de.havox_design.aoc2022.day05

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertNotNull

class StackTest {
    @Test
    fun testStack() {
        val expectedId = 42
        val expectedCrate1 = Crate("DEEP THOUGHT")
        val expectedCrate2 = Crate("HEART OF GOLD")

        val objectUnderTest = Stack.emptyStackWithId(expectedId)
        objectUnderTest.stack += expectedCrate1
        objectUnderTest.stack += expectedCrate2

        assertAll(
            { assertNotNull(objectUnderTest.id) },
            { objectUnderTest.id.shouldBe(expectedId) },
            { objectUnderTest.stack.shouldContainAll(listOf(expectedCrate1, expectedCrate2)) }
        )
    }
}
