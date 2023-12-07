package de.havox_design.aoc2023.day07

enum class Card(val symbol: Char, private val order: Int) {
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JOKER('J', 11),
    QUEEN('Q', 12),
    KING('K', 13),
    ACE('A', 14);

    companion object {
        fun from(c: Char): Card {
            if (entries.map { card -> card.symbol }.contains(c)) {
                return entries.first { card -> card.symbol == c }
            }

            throw IllegalArgumentException("No knows card symbol '${c}'")
        }

        fun compare(a: Card, b: Card): Int =
            a.order.compareTo(b.order)
    }
}
