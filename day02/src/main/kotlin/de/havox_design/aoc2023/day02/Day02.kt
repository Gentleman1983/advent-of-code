package de.havox_design.aoc2023.day02

class Day02(private var filename: String) {
    fun solvePart1(): Long =
        getResourceAsText(filename)
            .mapNotNull(::getGameIdOfValidInputLine)
            .sum()
            .toLong()

    fun solvePart2(): Long =
        0L

    private fun getGameIdOfValidInputLine(line: String): Int? {
        val (game, indicators) = line.split(":")
        val gameId = parseGameId(game)
        val subsets = parseSubsets(indicators)
        val valid = subsets.all(::isValidSubset)

        return if (valid) gameId else null
    }

    private fun parseGameId(game: String): Int =
        game
            .filter { it.isDigit() }
            .toInt()

    private fun parseSubsets(indicators: String): List<Pair<String, Int>> =
        indicators
            .split(";", ",")
            .map { sets -> sets.filter { it.isLetter() } to sets.filter { it.isDigit() }.toInt() }

    private fun isValidSubset(subset: Pair<String, Int>): Boolean {
        val (color, count) = subset

        return when {
            color == "red" && count <= 12 -> true
            color == "green" && count <= 13 -> true
            color == "blue" && count <= 14 -> true
            else -> false
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}