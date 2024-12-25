package de.havox_design.aoc2022.day06

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DetectionWindowTest {
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
}
