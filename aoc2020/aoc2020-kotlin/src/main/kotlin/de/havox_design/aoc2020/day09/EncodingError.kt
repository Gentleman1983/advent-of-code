package de.havox_design.aoc2020.day09

class EncodingError(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(preambleSize: Int = 25): Long =
        data
            .map { it.toLong() }
            .windowed(preambleSize + 1, partialWindows = false)
            .first { cantMakeFromParts(it) }
            .last()

    fun processPart2(preambleSize: Int = 25): Any {
        val nums = data
            .map { it.toLong() }
        val bad = nums
            .windowed(preambleSize + 1)
            .map { it.dropLast(1) to it.last() }
            .map { (head, tail) -> head.flatMapIndexed { i, x -> head.drop(i + 1).map { it + x } }.toSet() to tail }
            .first { (head, tail) -> tail !in head }
            .second
        val partials = nums
            .scan(0L) { acc, value -> acc + value }
        var from = 0
        var to = 2

        while (to in partials.indices) {
            val curr = partials[to] - partials[from]

            when {
                curr < bad -> to++
                curr > bad -> {
                    from++
                    to = maxOf(to, from + 2)
                }

                else -> break
            }
        }

        val subset = nums
            .subList(from, to)

        return subset.minOf { it } + subset.maxOf { it }
    }

    private fun cantMakeFromParts(numbers: List<Long>): Boolean {
        val total = numbers.last()
        val parts = numbers.subList(0, numbers.lastIndex)

        return !parts
            .any {
                val remainder = total - it

                remainder != it && parts.contains(remainder)
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
