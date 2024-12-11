package de.havox_design.aoc2024.day11

class PlutonianPebbles(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))
    private val cache = mutableMapOf<Pair<Long, Int>, Long>()

    fun processPart1(): Any =
        data
            .sumOf { stone ->
                stone
                    .countStones(cache, 25)
            }

    fun processPart2(): Any =
        data
            .sumOf { stone ->
                stone
                    .countStones(cache, 75)
            }

    @SuppressWarnings("kotlin:S6611")
    private fun Long.countStones(cache: MutableMap<Pair<Long, Int>, Long>, rounds: Int): Long {
        if (rounds == 0) {
            return 1
        }

        if (cache.contains(this to rounds)) {
            return cache[this to rounds]!!
        }

        val result =
            when {
                this == 0L ->
                    1L
                        .countStones(cache, rounds - 1)

                hasEvenDigitCount() ->
                    halves()
                        .sumOf { half ->
                            half
                                .countStones(cache, rounds - 1)
                        }

                else ->
                    (this * 2024)
                        .countStones(cache, rounds - 1)
            }

        cache[this to rounds] = result
        return result
    }

    private fun Long.hasEvenDigitCount() =
        toString()
            .length % 2 == 0

    private fun Long.halves() =
        listOf(
            toString()
                .substring(0, toString().length / 2),
            toString()
                .substring(toString().length / 2)
        )
            .map(String::toLong)

    private fun parseInput(input: List<String>): List<Long> =
        input[0]
            .split(" ")
            .map(String::toLong)

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
