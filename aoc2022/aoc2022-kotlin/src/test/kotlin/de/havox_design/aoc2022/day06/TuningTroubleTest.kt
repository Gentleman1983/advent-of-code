package de.havox_design.aoc2022.day06

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TuningTroubleTest {
    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day06/day06Sample1.txt,7",
        "de/havox_design/aoc2022/day06/day06Sample2.txt,5",
        "de/havox_design/aoc2022/day06/day06Sample3.txt,6",
        "de/havox_design/aoc2022/day06/day06Sample4.txt,10",
        "de/havox_design/aoc2022/day06/day06Sample5.txt,11"
    )
    fun testProcessPart1(filename: String, expectedValue: Int) =
        TuningTrouble(filename).processPart1().shouldBe(expectedValue)

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day06/day06Sample1.txt,19",
        "de/havox_design/aoc2022/day06/day06Sample2.txt,23",
        "de/havox_design/aoc2022/day06/day06Sample3.txt,23",
        "de/havox_design/aoc2022/day06/day06Sample4.txt,29",
        "de/havox_design/aoc2022/day06/day06Sample5.txt,26"
    )
    fun testProcessPart2(filename: String, expectedValue: Int) =
        TuningTrouble(filename).processPart2().shouldBe(expectedValue)
}
