package de.havox_design.aoc2017.day04

class HighEntropyPassphrases(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .count { it == it.split(" ").distinct().joinToString(" ") }

    fun processPart2(): Any =
        data
            .count { line ->
                val words = line
                    .split(" ")
                val pairs = words
                    .flatMapIndexed { i, a ->
                        words
                            .mapIndexedNotNull { j, b -> if (i != j) a to b else null }
                    }

                pairs
                    .all { (a, b) ->
                        a.toCharArray().sorted() != b.toCharArray().sorted()
                    }
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
