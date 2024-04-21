package de.havox_design.aoc2020.day13

class ShuttleSearch(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val start = data[ID_ARRIVAL]
            .toInt()
        val bus = data[ID_BUSSES]
            .split(DELIMITOR_BUSSES)
            .filter { it != ICON_BUSSES_OUT_OF_SERVICE }
            .map { it.toInt() }
            .minByOrNull { remainingMinutes(it, start) } ?: throw IllegalStateException()

        return bus * remainingMinutes(bus, start)
    }

    fun processPart2(): Any {
        val busses = data[ID_BUSSES]
            .split(DELIMITOR_BUSSES)
            .mapIndexedNotNull { index, id ->
                if (id == ICON_BUSSES_OUT_OF_SERVICE) {
                    null
                } else {
                    Bus(id.toLong(), index)
                }
            }
        val numbers = busses
            .map { it.id }
        val remainders = busses
            .map { it.id - it.offset.toLong() }

        return solveRemainders(numbers, remainders)
    }

    private fun remainingMinutes(bus: Int, start: Int) =
        bus - (start % bus)

    private fun solveRemainders(moduli: List<Long>, remainders: List<Long>): Long {
        val indices = moduli.indices
        val moduloProduct = moduli.product()
        val partialProducts = moduli.map { moduloProduct / it }
        val inverse = indices.map { modularMultiplicativeInverse(partialProducts[it], moduli[it]) }

        return indices
            .sumOf { remainders[it] * partialProducts[it] * inverse[it] } % moduloProduct
    }

    private fun modularMultiplicativeInverse(a: Long, m: Long): Long {
        val (g, x, _) = extendedGcd(a, m)
        val positiveX = makePositive(m, x)

        assert(g == 1L) { "$a and $m is not coprime" }
        assert(a * positiveX % m == 1L) { "$a * $positiveX % $m != 1" }
        assert(positiveX in 0 until m) { "x not in range 0..${m - 1}" }

        return positiveX
    }

    private fun makePositive(m: Long, x: Long) =
        (m + x) % m

    private fun extendedGcd(a: Long, b: Long): ExtendedGcd {
        return when {
            a == 0L -> ExtendedGcd(b, 0, 1)
            b == 0L -> ExtendedGcd(a, 1, 0)
            else -> {
                val (g, x1, y1) = extendedGcd(b, a % b)
                val x = y1
                val y = x1 - y1 * (a / b)
                ExtendedGcd(g, x, y)
            }
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
        private const val DELIMITOR_BUSSES = ","
        private const val ICON_BUSSES_OUT_OF_SERVICE = "x"
        private const val ID_ARRIVAL = 0
        private const val ID_BUSSES = 1
    }
}

private fun Iterable<Long>.product() =
    reduce { acc, item -> acc * item }
