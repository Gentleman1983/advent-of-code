package de.havox_design.aoc2022.day06

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day06Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @CsvSource(
        "abc,false",
        "abcd,true",
        "abcde,false"
    )
    fun testHasDetectionWindowCorrectLength(data: String, expectedLengthCorrect: Boolean) =
        DetectionWindow(data).hasCorrectLength(4).shouldBe(expectedLengthCorrect)

    @ParameterizedTest
    @CsvSource(
        "abc,false",
        "aacd,false",
        "bbcd,false",
        "abcd,true",
        "abdd,false",
        "abcc,false",
        "xxxx,false",
        "abcde,false"
    )
    fun testIsDetectionWindowPacketMarker(data: String, expectedLengthCorrect: Boolean) =
        DetectionWindow(data).isPacketMarker().shouldBe(expectedLengthCorrect)

    @ParameterizedTest
    @CsvSource(
        "abcdefghijklm,false",
        "aaaaaaaaaaaaaa,false",
        "aaaaaabbbbbbbb,false",
        "abcdefghijklmn,true",
        "aabbccddeeffgg,false",
        "abcdefghijklmno,false"
    )
    fun testIsDetectionWindowMessageMarker(data: String, expectedLengthCorrect: Boolean) =
        DetectionWindow(data).isMessageMarker().shouldBe(expectedLengthCorrect)

    @ParameterizedTest
    @CsvSource(
        "day06Sample1.txt,7",
        "day06Sample2.txt,5",
        "day06Sample3.txt,6",
        "day06Sample4.txt,10",
        "day06Sample5.txt,11"
    )
    fun testProcessPart1(filename: String, expectedValue: Int) =
        TuningTrouble(filename).processPart1().shouldBe(expectedValue)

    @ParameterizedTest
    @CsvSource(
        "day06Sample1.txt,19",
        "day06Sample2.txt,23",
        "day06Sample3.txt,23",
        "day06Sample4.txt,29",
        "day06Sample5.txt,26"
    )
    fun testProcessPart2(filename: String, expectedValue: Int) =
        TuningTrouble(filename).processPart2().shouldBe(expectedValue)
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
