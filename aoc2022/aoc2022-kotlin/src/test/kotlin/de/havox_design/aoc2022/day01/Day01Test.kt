package de.havox_design.aoc2022.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun singleEntry() =
        CaloriesCounter("de/havox_design/aoc2022/day01/day01SingleEntry.txt")
            .processFile()
            .shouldBe("42")


    @Test
    fun singleSum() =
        CaloriesCounter("de/havox_design/aoc2022/day01/day01SingleSum.txt")
            .processFile()
            .shouldBe("797")

    @Test
    fun sample() =
        CaloriesCounter("de/havox_design/aoc2022/day01/day01Sample.txt")
            .processFile()
            .shouldBe("24000")

    @Test
    fun sampleTopThree() =
        CaloriesCounter("de/havox_design/aoc2022/day01/day01Sample.txt")
            .processFileTopThree()
            .shouldBe("45000")
}

private fun String.shouldBe(expectation: String) = assertEquals(expectation, this)
