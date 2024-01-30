package de.havox_design.aoc2023.day07

import de.havox_design.aoc.utils.kotlin.model.cards.Card

class Hand(val cards: List<Card>, private val isPartOne: Boolean) : Comparable<Hand> {
    override fun compareTo(other: Hand): Int =
        compare(this, other, isPartOne)

    companion object {
        fun from(input: String, isPartOne: Boolean): Hand {
            val hand = ArrayList<Card>()

            for (c in input.toCharArray()) {
                hand.add(Card.from(c))
            }

            return Hand(hand, isPartOne)
        }

        fun compare(a: Hand, b: Hand, isPartOne: Boolean): Int {
            if (a == b) {
                return 0
            }

            val aGameType = GameType.from(a, isPartOne)
            val bGameType = GameType.from(b, isPartOne)

            if (aGameType == bGameType) {
                for (index in a.cards.indices) {
                    if (a.cards[index] == b.cards[index]) {
                        continue
                    }

                    return Card.compare(a.cards[index], b.cards[index], isPartOne)
                }
            }

            return GameType.compare(aGameType, bGameType)
        }
    }
}