package de.havox_design.aoc2021.day21

import de.havox_design.aoc.utils.kotlin.helpers.cartesianProduct
import kotlin.math.max
import kotlin.math.min

class DiracDice(private var filename: String) {
    private val DELIMITER_STARTING_POSITION = ": "
    private val data = getResourceAsText(filename)
    val cache = mutableMapOf<GameState, WinCount>()
    val possibleRolls = (1..3)
        .cartesianProduct(1..3) { a, b -> a + b }
        .cartesianProduct(1..3) { a, b -> a + b }

    fun processPart1(): Any {
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

    fun processPart2(): Any {
        val player1 = data
            .first()
            .substringAfter(DELIMITER_STARTING_POSITION)
            .toInt()
        val player2 = data
            .last()
            .substringAfter(DELIMITER_STARTING_POSITION)
            .toInt()
        val (p1WinCount, p2WinCount) = getWinCount(GameState(player1, 0, player2, 0))

        return max(p1WinCount, p2WinCount)
    }

    @SuppressWarnings("kotlin:S6510")
    private fun getWinCount(currentState: GameState): WinCount {
        val cachedState = cache[currentState]

        when {
            cachedState != null -> {
                return cachedState
            }
            currentState.score1 >= 21 -> {
                return WinCount(1, 0)
            }
            currentState.score2 >= 21 -> {
                return WinCount(0, 1)
            }
            else -> {
                val finalWinCount = possibleRolls.map { roll1 ->
                    val pos1 = (currentState.pos1 + roll1 - 1) % 10 + 1
                    val score1 = currentState.score1 + pos1
                    val nextState = GameState(currentState.pos2, currentState.score2, pos1, score1)

                    getWinCount(nextState).inverted()
                }
                    .reduce { acc, result -> acc + result }

                cache[currentState] = finalWinCount

                return finalWinCount
            }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
