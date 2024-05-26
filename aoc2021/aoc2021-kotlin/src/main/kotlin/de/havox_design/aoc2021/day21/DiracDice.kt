package de.havox_design.aoc2021.day21

import kotlin.math.min

class DiracDice(private var filename: String) {
    private val DELIMITER_STARTING_POSITION = ": "

    private val data = getResourceAsText(filename)

    fun processPart1(): Any  {
        var player1 = data
            .first()
            .substringAfter(DELIMITER_STARTING_POSITION)
            .toInt()
        var player2 = data
            .last()
            .substringAfter(DELIMITER_STARTING_POSITION)
            .toInt()
        val die = CircularIterator(1..100)
        var score1 = 0
        var score2 = 0
        var dieRolls = 0

        while (score1 < 1_000 && score2 < 1_000) {
            dieRolls += 3

            val roll1 = (1..3)
                .sumOf { die.next() }

            player1 = (player1 + roll1 - 1) % 10 + 1
            score1 += player1

            if (score1 < 1_000) {
                val roll2 = (1..3)
                    .sumOf { die.next() }

                dieRolls += 3
                player2 = (player2 + roll2 - 1) % 10 + 1
                score2 += player2
            }
        }

        return min(score1, score2) * dieRolls
    }

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
