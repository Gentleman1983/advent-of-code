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

    fun processPart2(): Any {
        val fullList = getFullList(data)
        val diffs = getDiffs(fullList)
        var total = 1L
        var currentIndex = 0

        while (currentIndex != diffs.lastIndex) {
            val nextCheckpoint = nextCheckpoint(currentIndex, diffs)

            if (nextCheckpoint - currentIndex > 1) {
                total *= count(0, fullList.subList(currentIndex, nextCheckpoint + 1))
            }

            currentIndex = nextCheckpoint
        }

        return total
    }

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

    private fun nextCheckpoint(currentIndex: Int, diffs: List<Int>): Int {
        val offset = currentIndex + 1

        return diffs
            .subList(offset, diffs.size)
            .indexOf(3) + offset
    }

    private fun count(currentIndex: Int, sortedList: List<Int>): Long {
        if (currentIndex == sortedList.lastIndex) {
            return 1
        }

        val currentValue = sortedList[currentIndex]

        return List(
            sortedList
                .subList(currentIndex + 1, sortedList.size)
                .takeWhile { it - currentValue <= 3 }.size
        ) { index ->
            count(currentIndex + index + 1, sortedList)
        }
            .sum()
    }


    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
