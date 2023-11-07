package de.havox_design.aoc2023.day24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day24Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }
}

private fun String.shouldBe(expectation: String) = assertEquals(expectation, this)
