package de.havox_design.aoc2023.day07

class Hand(val cards: List<Card>): Comparable<Hand> {
    override fun compareTo(other: Hand): Int =
        compare(this, other)

    companion object{
        fun from(input: String): Hand {
            val hand = ArrayList<Card>()

            for(c in input.toCharArray()) {
                hand.add(Card.from(c))
            }

            return Hand(hand)
        }

        fun compare(a: Hand, b: Hand): Int {
            if(a == b) {
                return 0
            }

            val aGameType = GameType.from(a)
            val bGameType = GameType.from(b)

            if(aGameType == bGameType) {
                for(index in a.cards.indices) {
                    if(a.cards[index] == b.cards[index]) {
                        continue
                    }

                    return Card.compare(a.cards[index], b.cards[index])
                }
            }

            return GameType.compare(aGameType, bGameType)
        }
    }
}