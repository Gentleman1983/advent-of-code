package de.havox_design.aoc2024.day06

class GuardGallivant(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val initialPosition =
        data
            .withIndex()
            .firstNotNullOf { (y, line) ->
                val x = line.indexOf('^')
                if (x >= 0) y to x else null
            }
    private val initialWalk: Set<IntPair> by lazy(LazyThreadSafetyMode.PUBLICATION) {
        data
            .walk(initialPosition)
            .mapTo(mutableSetOf()) { it.first }
    }

    fun processPart1(): Any =
        initialWalk
            .size

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private fun List<String>.walk(position: IntPair) =
            sequence {
                var (y, x) = position
                var dy = -1
                var dx = 0

                while (true) {
                    yield(Pair(y to x, dy to dx))
                    val nextY = y + dy
                    val nextX = x + dx

                    when (getOrNull(nextY)?.getOrNull(nextX)) {
                        null -> break
                        '#' -> dy = dx.also { dx = -dy }
                        else -> {
                            y = nextY
                            x = nextX
                        }
                    }
                }
            }
    }
}
