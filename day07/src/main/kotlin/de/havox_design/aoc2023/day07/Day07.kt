package de.havox_design.aoc2023.day07

class Day07(private var filename: String) {
    private val ELEMENT_DELIMITER = " "

    fun solvePart1(): Long {
        val data = mapInput(getResourceAsText(filename))
        val sortedData = data.sortedBy { game -> game.first }.toList()
        var winnings = 0L

        for(index in sortedData.indices) {
            winnings += (index + 1) * sortedData[index].second
        }

        return winnings
    }

    fun solvePart2(): Long =
        0L

    private fun mapInput(input: List<String>):List<Pair<Hand,Int>> =
        input
            .map { row ->
                val elements = row.split(ELEMENT_DELIMITER)
                val hand = Hand.from(elements[0])
                val bit = elements[1].toInt()

                return@map Pair(hand, bit)
            }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}