package de.havox_design.aoc2023.day04

data class ScratchCard(val winningNumbers: List<Int>, val playerNumbers: List<Int>) {
    fun scoreV1(): Long {
        var score = 0L
        playerNumbers
            .forEach { number ->
            if (number in winningNumbers && score == 0L) {
                score = 1L
            } else if (number in winningNumbers) {
                score *= 2
            }
        }
        return score
    }

    companion object {
        private const val NAME_END_DELIMITER: String = ":"
        private const val DATA_DELIMITER: String = "|"
        private const val VALUE_DELIMITER: String = " "

        fun from(entry: String) = ScratchCard(
            winningNumbers = entry
                .substringAfter(NAME_END_DELIMITER)
                .substringBefore(DATA_DELIMITER)
                .split(VALUE_DELIMITER)
                .filter(String::isNotBlank)
                .map(String::toInt),
            playerNumbers = entry
                .substringAfter(DATA_DELIMITER)
                .split(VALUE_DELIMITER)
                .filter(String::isNotBlank)
                .map(String::toInt),
        )
    }
}
