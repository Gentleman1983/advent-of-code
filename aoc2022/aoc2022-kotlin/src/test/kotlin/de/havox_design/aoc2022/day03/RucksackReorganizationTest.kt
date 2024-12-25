package de.havox_design.aoc2022.day03

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RucksackReorganizationTest {
    @ParameterizedTest
    @MethodSource("getDataForTestGetDuplicatesForFile")
    fun testGetDuplicatesForFile(filename: String, expectedDuplicates: List<Item>) =
        RucksackReorganization(filename)
            .getDuplicatesFromList()
            .shouldContainAll(expectedDuplicates)

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day03/day03SampleRow1.txt,16",
        "de/havox_design/aoc2022/day03/day03SampleRow2.txt,38",
        "de/havox_design/aoc2022/day03/day03SampleRow3.txt,42",
        "de/havox_design/aoc2022/day03/day03SampleRow4.txt,22",
        "de/havox_design/aoc2022/day03/day03SampleRow5.txt,20",
        "de/havox_design/aoc2022/day03/day03SampleRow6.txt,19",
        "de/havox_design/aoc2022/day03/day03Sample.txt,157"
    )
    fun testGetScoresForFile(filename: String, expectedScore: Int) =
        RucksackReorganization(filename)
            .getDuplicatesScoreFromList()
            .shouldBe(expectedScore)

    @ParameterizedTest
    @MethodSource("getDataForTestGetBadgesFromFile")
    fun testGetBadgesFromFile(filename: String, expectedBadges: List<Item>) {
        RucksackReorganization(filename)
            .detectBadgesFromList()
            .shouldContainAll(expectedBadges)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestGetBadgeScoreFromFile")
    fun testGetBadgeScoreFromFile(filename: String, expectedScore: Int) {
        RucksackReorganization(filename)
            .getBadgesScoreFromList()
            .shouldBe(expectedScore)
    }


    companion object {
        @JvmStatic
        private fun getDataForTestGetDuplicatesForFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow1.txt", listOf(Item("p"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow2.txt", listOf(Item("L"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow3.txt", listOf(Item("P"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow4.txt", listOf(Item("v"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow5.txt", listOf(Item("t"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03SampleRow6.txt", listOf(Item("s"))),
                Arguments.of(
                    "de/havox_design/aoc2022/day03/day03Sample.txt", listOf(
                        Item("p"),
                        Item("L"),
                        Item("P"),
                        Item("v"),
                        Item("t"),
                        Item("s")
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestGetBadgesFromFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day03/day03Badges1.txt", listOf(Item("r"))),
                Arguments.of("de/havox_design/aoc2022/day03/day03Badges2.txt", listOf(Item("Z"))),
                Arguments.of(
                    "de/havox_design/aoc2022/day03/day03Sample.txt",
                    listOf(
                        Item("r"),
                        Item("Z")
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestGetBadgeScoreFromFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day03/day03Badges1.txt", 18),
                Arguments.of("de/havox_design/aoc2022/day03/day03Badges2.txt", 52),
                Arguments.of("de/havox_design/aoc2022/day03/day03Sample.txt", 70)
            )
    }
}
