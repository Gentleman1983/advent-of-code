package de.havox_design.aoc.utils.kotlin.model.cards

enum class Card(val symbol: Char, private val orderPartOne: Int, private val orderPartTwo: Int) {
    TWO('2', 2, 2),
    THREE('3', 3, 3),
    FOUR('4', 4, 4),
    FIVE('5', 5, 5),
    SIX('6', 6, 6),
    SEVEN('7', 7, 7),
    EIGHT('8', 8, 8),
    NINE('9', 9, 9),
    TEN('T', 10, 10),
    JOKER('J', 11, 1),
    QUEEN('Q', 12, 12),
    KING('K', 13, 13),
    ACE('A', 14, 14);

    companion object {
        fun from(c: Char): Card {
            if (entries.map { card -> card.symbol }.contains(c)) {
                return entries
                    .first { card -> card.symbol == c }
            }

            throw IllegalArgumentException("No knows card symbol '${c}'")
        }

        fun compare(a: Card, b: Card, partOne: Boolean): Int {
            if (partOne) {
                return a.orderPartOne.compareTo(b.orderPartOne)
            }

            return a.orderPartTwo.compareTo(b.orderPartTwo)
        }
    }
}
