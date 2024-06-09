package de.havox_design.aoc2023.day02

class CubeConundrum(private var filename: String) {
    fun solvePart1(): Long =
        getResourceAsText(filename)
            .mapNotNull(::getGameIdOfValidInputLine)
            .sum()
            .toLong()

    fun solvePart2(): Long =
        getResourceAsText(filename).sumOf { game ->
            val indicators = parseIndicators(game)
            val subsets = parseSubsets(indicators)
            val (minRed, minGreen, minBlue) = getMinSetOfCubes(subsets)

            (minRed * minGreen * minBlue).toLong()
        }

    private fun getGameIdOfValidInputLine(line: String): Int? {
        val (game, indicators) = line.split(":")
        val gameId = parseGameId(game)
        val subsets = parseSubsets(indicators)
        val valid = subsets.all(::isValidSubset)

        return if (valid) {
            gameId
        } else {
            null
        }
    }

    private fun parseGameId(game: String): Int =
        game
            .filter { it.isDigit() }
            .toInt()

    private fun parseSubsets(indicators: String): List<Pair<String, Int>> =
        indicators
            .split(";", ",")
            .map { sets -> sets.filter { it.isLetter() } to sets.filter { it.isDigit() }.toInt() }

    private fun parseIndicators(string: String) =
        string
            .split(":")
            .last()

    private fun isValidSubset(subset: Pair<String, Int>): Boolean {
        val (color, count) = subset

        return when {
            color == "red" && count <= 12 -> true
            color == "green" && count <= 13 -> true
            color == "blue" && count <= 14 -> true
            else -> false
        }
    }

    @SuppressWarnings("kotlin:S6611")
    private fun getMinSetOfCubes(subsets: List<Pair<String, Int>>): Triple<Int, Int, Int> {

        val countMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        subsets
            .forEach { (color, count) ->
                if (count > countMap[color]!!) {
                    countMap[color] = count
                }
            }

        return Triple(countMap["red"]!!, countMap["green"]!!, countMap["blue"]!!)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}