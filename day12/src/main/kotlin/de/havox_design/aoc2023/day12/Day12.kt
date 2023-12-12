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
        getResourceAsText(filename)
            .map { parseInput(it, true) }
            .sumOf {
                it
                    .possibleGroups
                    .count()
            }

    private fun parseInput(row: String, folded: Boolean = false): Record {
        val (text, groups) = row
            .split(DATA_DELIMITER)
        val groupDistribution = groups
            .split(GROUPS_DELIMITER)
            .map { it.toInt() }

        return when {
            folded -> {
                Record(
                    "$text?$text?$text?$text?$text",
                    listOf(groupDistribution, groupDistribution, groupDistribution, groupDistribution, groupDistribution)
                        .flatten()
                )
            }

            else -> Record(text, groupDistribution)
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}