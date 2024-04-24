package de.havox_design.aoc2020.day20

class JurassicJigsaw(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val tiles = data
            .replace(CARRIAGE_RETURN, EMPTY)
            .split(DOUBLE_NEWLINE)
            .map { it.lines() }
            .map { (NUMBER.find(it.first())?.value?.toIntOrNull() ?: 0) to it.drop(1).map { l -> l.toList() } }
            .map { it.first to profiles(it.second) }
        val profileCounts = tiles
            .flatMap { it.second }
            .groupingBy { it }
            .eachCount()
        val corners = tiles
            .filter { it.second.count { prof -> profileCounts[prof] == 1 } == 4 }

        return corners
            .map { it.first.toLong() }
            .reduce(Long::times)
    }

    fun processPart2(): Any =
        0L

    private fun profiles(tile: List<List<Char>>): List<Int> {
        val top = tile.first()
        val bottom = tile.last()
        val left = tile.map { it.first() }
        val right = tile.map { it.last() }

        return listOf(left, top, right, bottom)
            .map {
                it
                .joinToString(separator = EMPTY)
                .replace(LITERAL_WHITE, LITERAL_ONE)
                .replace(LITERAL_BLACK, LITERAL_ZERO)
            }
            .flatMap { listOf(it.toInt(RADIX_BINARY), it.reversed().toInt(RADIX_BINARY)) }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val CARRIAGE_RETURN = "\r"
        private const val DOUBLE_NEWLINE = "\n\n"
        private const val EMPTY = ""
        private const val LITERAL_BLACK = '.'
        private const val LITERAL_ONE = '1'
        private const val LITERAL_WHITE = '#'
        private const val LITERAL_ZERO = '0'
        private const val RADIX_BINARY = 2

        private val NUMBER = "\\d+".toRegex()
    }
}
