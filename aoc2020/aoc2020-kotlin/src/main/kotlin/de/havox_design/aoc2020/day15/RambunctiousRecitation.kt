package de.havox_design.aoc2020.day15

class RambunctiousRecitation(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val startingNumbers = data
            .split(DELIMITOR_VALUES)
            .map { it.toInt() }

        return playTurns(startingNumbers, 2020)
    }

    fun processPart2(): Any =
        0L

    private fun playTurns(startingNumbers: List<Int>, turns: Int): Int {
        val history = mutableMapOf<Int, Int>()

        (0 until startingNumbers.lastIndex)
            .forEach {
                history[startingNumbers[it]] = it + 1
            }

        return (startingNumbers.size until turns)
            .fold(startingNumbers.last()) { lastNumber, turn ->
                val nextNumber = history[lastNumber]
                    ?.let { turn - it } ?: 0

                history[lastNumber] = turn
                nextNumber
            }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val DELIMITOR_VALUES = ","
    }
}
