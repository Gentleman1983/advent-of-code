package de.havox_design.aoc2023.day04

class Day04(private var filename: String) {
    fun solvePart1(): Long =
        getResourceAsText(filename)
            .filter(String::isNotBlank)
            .map { ScratchCard.from(it) }
            .sumOf { it.scoreV1() }

    fun solvePart2(): Long {
        val cards = getResourceAsText(filename)
            .filter(String::isNotBlank)
            .map { ScratchCard.from(it) }

        for (i in cards.indices) {
            val winnings = cards[i]
                .countMatching()
            val originalCount = cards[i].count
            for (w in 1..winnings) {
                cards[i + w].count += originalCount
            }
        }

        return cards
            .sumOf { it.count }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}