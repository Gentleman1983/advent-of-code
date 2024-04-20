package de.havox_design.aoc2020.day10

class AdapterArray(private var filename: String) {
    private val data = getResourceAsText(filename)

    @SuppressWarnings("kotlin:S6611")
    fun processPart1(): Any {
        val all = getFullList(data)

        val mapValues = getDiffs(all)
            .groupBy { it }
            .mapValues { (_, value) -> value.size }

        return mapValues[1]!! * mapValues[3]!!
    }

    fun processPart2(): Any =
        0L

    private fun getFullList(lines: List<String>): List<Int> {
        val sorted = lines
            .map { it.toInt() }
            .sorted()

        return listOf(0) + sorted + (sorted.last() + 3)
    }

    private fun getDiffs(all: List<Int>) =
        all
            .zipWithNext()
            .map { (a, b) -> b - a }


    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
