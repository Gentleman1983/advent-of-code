package de.havox_design.aoc2024.day04

class CeresSearch(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .indices
            .sumOf { y ->
                data[y]
                    .indices
                    .sumOf { x ->
                        Direction
                            .entries
                            .count { (dx, dy) ->
                                XMAS
                                    .withIndex()
                                    .all { (i, c) ->
                                        data
                                            .getOrNull(y + i * dy)
                                            ?.getOrNull(x + i * dx) == c
                                    }
                            }
                    }
            }

    fun processPart2(): Any =
        data
            .indices
            .sumOf { y ->
                data[y]
                    .indices
                    .count { x ->
                        if (data[y][x] != 'A') {
                            return@count false
                        }

                        val n = data.getOrNull(y - 1) ?: return@count false
                        val s = data.getOrNull(y + 1) ?: return@count false

                        setOf(n.getOrNull(x - 1), s.getOrNull(x + 1)) == MS &&
                                setOf(n.getOrNull(x + 1), s.getOrNull(x - 1)) == MS
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
        private const val XMAS = "XMAS"
        private val MS = setOf('M', 'S')
    }
}
