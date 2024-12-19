package de.havox_design.aoc2024.day19

class LinenLayout(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any =
        data
            .designs
            .fold(0) { r, design ->
                r + if (isDesignMatching(data.patterns, design) > 0L) {
                    1
                } else {
                    0
                }
            }

    fun processPart2(): Any =
        0L

    @SuppressWarnings("kotlin:S6611")
    private fun isDesignMatching(patterns: List<String>, design: String): Long {
        val lengthWays = mutableMapOf(0 to 1L)

        for (patternEnd in 1..design.length) {
            val possiblePatternStarts = patterns
                .mapNotNull { pattern ->
                    val patternStart = patternEnd - pattern.length

                    lengthWays[patternStart]
                        ?.takeIf { design.substring(patternStart, patternEnd) == pattern }
                }

            lengthWays[patternEnd] = possiblePatternStarts
                .sum()
        }

        return lengthWays[design.length]!!
    }

    private fun parseInput(input: List<String>): LinenData =
        LinenData(
            input
                .first()
                .split(", "),
            input
                .drop(2)
        )

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
