package de.havox_design.aoc2022.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @Test
    fun singleEntry() =
        CaloriesCounter("day01SingleEntry.txt")
            .processFile()
            .shouldBe("42")


    @Test
    fun singleSum() =
        CaloriesCounter("day01SingleSum.txt")
            .processFile()
            .shouldBe("797")

    @Test
    fun sample() =
        CaloriesCounter("day01Sample.txt")
            .processFile()
            .shouldBe("24000")

    @Test
    fun sampleTopThree() =
        CaloriesCounter("day01Sample.txt")
            .processFileTopThree()
            .shouldBe("45000")
}

private fun String.shouldBe(expectation: String) = assertEquals(expectation, this)
