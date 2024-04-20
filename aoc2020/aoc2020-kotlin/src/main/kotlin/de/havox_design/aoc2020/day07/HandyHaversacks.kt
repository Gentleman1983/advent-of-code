package de.havox_design.aoc2020.day07

class HandyHaversacks(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        buildGraph(data)[MY_BAG_TYPE]
            .containedInNames()
            .size

    fun processPart2(): Any =
        0L

    private fun buildGraph(lines: List<String>): Graph {
        return Graph()
            .apply {
                lines
                    .forEach(this::addRule)
            }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val MY_BAG_TYPE = "shiny gold"
    }
}
