package de.havox_design.aoc2024.day01

import kotlin.math.abs

class HistorianHysteria(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val lists = buildLists()
    private val multiplicityMap = buildMultiplicityMap()

    @SuppressWarnings("kotlin:S6524")
    fun processPart1(): Any {
        val leftSorted = lists
            .first
            .stream()
            .sorted()
            .toList()
        val rightSorted = lists
            .second
            .stream()
            .sorted()
            .toList()
        var currentSum = 0L

        for (index in leftSorted.indices) {
            currentSum += abs(leftSorted[index] - rightSorted[index])
        }

        return currentSum
    }

    fun processPart2(): Long =
        lists
            .first
            .map { value ->
                multiplicityMap[value]
                    ?.times(value)
                    ?: 0L
        }
            .sumOf { it }

    @SuppressWarnings("kotlin:S3958")
    private fun buildMultiplicityMap(): Map<Long, Long> {
        val map = HashMap<Long, Long>()

        for (distinctValue in lists.first.stream().distinct().toList()) {
            map[distinctValue] = lists
                .second
                .stream()
                .filter { value ->
                    value == distinctValue
                }
                .count()
        }

        return map
    }

    private fun buildLists(): Pair<List<Long>, List<Long>> {
        val leftList = ArrayList<Long>()
        val rightList = ArrayList<Long>()

        for (input: String in data) {
            val values = input.split("   ")

            if (values.size == 2) {
                leftList
                    .add(values[0].toLong())
                rightList
                    .add(values[1].toLong())
            }
        }

        return Pair(leftList, rightList)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
