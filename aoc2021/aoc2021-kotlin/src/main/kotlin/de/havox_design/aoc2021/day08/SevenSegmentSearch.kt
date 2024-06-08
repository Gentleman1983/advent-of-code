package de.havox_design.aoc2021.day08

import de.havox_design.aoc.utils.kotlin.helpers.digitsToInt
import de.havox_design.aoc.utils.kotlin.helpers.pivot

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
        data
            .asSequence()
            .map { it.split(DELIMITOR_SAMPLES_OUTPUT) }
            .map { (observedValues, outputValues) ->
                observedValues.split(DELIMITOR_ELEMENTS) to outputValues.split(
                    DELIMITOR_ELEMENTS
                )
            }
            .sumOf { (observedValues, outputValues) ->
                solveOutputValues(
                    observedValues.map { it.toSet() },
                    outputValues.map { it.toSet() })
            }

    @SuppressWarnings("kotlin:S6611")
    private fun solveOutputValues(observedValues: List<Set<Char>>, outputValues: List<Set<Char>>): Int {
        val uniqueLengthNumbers = mapOf(1 to 2, 4 to 4, 7 to 3, 8 to 7)
        val observedValuesByLength = observedValues.groupBy { it.size }
        val knownPatterns = mutableMapOf<Int, Set<Char>>()

        uniqueLengthNumbers
            .forEach { (number, length) ->
                val observedSegment = observedValuesByLength[length]?.first()

                if (observedSegment != null) {
                    knownPatterns[number] = observedSegment
                }
            }

        observedValuesByLength[5]
            ?.forEach {
                val pattern7 = knownPatterns[7]!!
                val number = when {
                    it.containsAll(pattern7) -> 3
                    it.union(knownPatterns[4]!!).size == 7 -> 2
                    else -> 5
                }
                knownPatterns[number] = it
            }

        observedValuesByLength[6]
            ?.forEach {
                val pattern1 = knownPatterns[1]!!
                val pattern4 = knownPatterns[4]!!

                val number = when {
                    it.containsAll(pattern4) -> 9
                    it.containsAll(pattern1) -> 0
                    else -> 6
                }

                knownPatterns[number] = it
            }

        val mappings = knownPatterns.pivot()

        return outputValues
            .map { mappings[it]!! }
            .digitsToInt(10)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
