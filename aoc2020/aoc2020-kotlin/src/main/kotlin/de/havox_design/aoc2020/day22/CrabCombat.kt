package de.havox_design.aoc2020.day22

import java.util.*

class CrabCombat(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val (part1, part2) = data
            .replace(CARRIAGE_RETURN, EMPTY)
            .split(DOUBLE_NEWLINE)

        val player1Cards: Queue<Int> = LinkedList(part1.trim().lines().drop(1).map { it.toInt() })
        val player2Cards: Queue<Int> = LinkedList(part2.trim().lines().drop(1).map { it.toInt() })

        while (player1Cards.isNotEmpty() && player2Cards.isNotEmpty()) {
            val player1 = player1Cards.poll()
            val player2 = player2Cards.poll()

            if (player1 > player2) {
                player1Cards.add(player1)
                player1Cards.add(player2)
            } else {
                player2Cards.add(player2)
                player2Cards.add(player1)
            }
        }

        val winningCards = player1Cards.ifEmpty {
            player2Cards
        }

        return winningCards
            .mapIndexed { i, value -> (winningCards.size - i) * value }
            .sum()
    }

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object{
        private const val CARRIAGE_RETURN = "\r"
        private const val DOUBLE_NEWLINE = "\n\n"
        private const val EMPTY = ""
    }
}
