package de.havox_design.aoc2022.day01

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Test

class CaloriesCounterTest {
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
