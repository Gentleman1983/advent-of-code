package de.havox_design.aoc2023.day07

enum class GameType(private var order: Int) {
    FIVE_OF_A_KIND(6),
    FOUR_OF_A_KIND(5),
    FULL_HOUSE(4),
    THREE_OF_A_KIND(3),
    TWO_PAIRS(2),
    ONE_PAIR(1),
    HIGH_CARD(0);

    companion object {
        fun from(hand: Hand): GameType {
            val cardMap = getCardMap(hand)

            if(cardMap.size == 1) {
                return FIVE_OF_A_KIND
            }
            if(cardMap.size == 2) {
                if(cardMap.values.max() == 4) {
                    return FOUR_OF_A_KIND
                }
                return FULL_HOUSE
            }
            if(cardMap.size == 3) {
                if(cardMap.values.max() == 3) {
                    return THREE_OF_A_KIND
                }
                return TWO_PAIRS
            }
            if(cardMap.values.max() == 2) {
                return ONE_PAIR
            }
            return HIGH_CARD
        }

        fun compare(a: GameType, b: GameType): Int =
            a.order.compareTo(b.order)

        private fun getCardMap(hand: Hand): Map<Card, Int> {
            val result = HashMap<Card, Int>()

            for(c in hand.cards) {
                if(result.containsKey(c)) {
                    result[c] = result[c]!! + 1
                }
                else {
                    result[c] = 1
                }
            }

            return result
        }
    }
}