package de.havox_design.aoc2022.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day02Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @CsvSource(
        "day02Rock_rock.txt, 4",
        "day02Rock_paper.txt, 8",
        "day02Rock_scissors.txt, 3",
        "day02Paper_rock.txt, 1",
        "day02Paper_paper.txt, 5",
        "day02Paper_scissors.txt, 9",
        "day02Scissors_rock.txt, 7",
        "day02Scissors_paper.txt, 3",
        "day02Scissors_scissors.txt, 6",
        "day02Sample.txt, 15"
    )
    fun testPlaybooks(filename: String, expectedScore: Int) {
        RockPaperScissorsGame(filename)
            .getResultForGuide()
            .shouldBe(expectedScore)
    }

    @ParameterizedTest
    @CsvSource(
        "day02Rock_win.txt, 8",
        "day02Rock_draw.txt, 4",
        "day02Rock_loss.txt, 3",
        "day02Paper_win.txt, 9",
        "day02Paper_draw.txt, 5",
        "day02Paper_loss.txt, 1",
        "day02Scissors_win.txt, 7",
        "day02Scissors_draw.txt, 6",
        "day02Scissors_loss.txt, 2",
        "day02Sample.txt, 12"
    )
    fun testGameByExpectedResults(filename: String, expectedScore: Int) {
        RockPaperScissorsGame(filename)
            .getResultForExpectedResult()
            .shouldBe(expectedScore)
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
