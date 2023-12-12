package de.havox_design.aoc2023.day12

class Day12(private var filename: String) {
    private val DATA_DELIMITER = " "
    private val GROUPS_DELIMITER = ","

    fun solvePart1(): Long =
        getResourceAsText(filename)
            .map { parseInput(it) }
            .sumOf {
                it
                    .possibleGroups
                    .count()
            }

    fun solvePart2(): Long =
        0L

    private fun parseInput(row: String): Record {
        val (text, groups) = row
            .split(DATA_DELIMITER)

        return Record(
            text,
            groups
                .split(GROUPS_DELIMITER)
                .map { it.toInt() }
        )
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}