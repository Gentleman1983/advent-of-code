package de.havox_design.aoc2021.day08

class SevenSegmentSearch(private var filename: String) {
    private val DELIMITOR_ELEMENTS = " "
    private val DELIMITOR_SAMPLES_OUTPUT = " | "
    private val NUMBER_OF_SEGMENTS_EIGHT = 7
    private val NUMBER_OF_SEGMENTS_FOUR = 4
    private val NUMBER_OF_SEGMENTS_ONE = 2
    private val NUMBER_OF_SEGMENTS_SEVEN = 3
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .map { it.split(DELIMITOR_SAMPLES_OUTPUT) }
            .flatMap { (_, output) ->
                output
                    .split(DELIMITOR_ELEMENTS)
            }
            .count {
                it.length in setOf(
                    NUMBER_OF_SEGMENTS_ONE,
                    NUMBER_OF_SEGMENTS_SEVEN,
                    NUMBER_OF_SEGMENTS_FOUR,
                    NUMBER_OF_SEGMENTS_EIGHT
                )
            }

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
