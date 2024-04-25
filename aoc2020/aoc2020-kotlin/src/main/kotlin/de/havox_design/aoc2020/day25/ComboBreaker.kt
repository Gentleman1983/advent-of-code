package de.havox_design.aoc2020.day25

class ComboBreaker(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val items = data
            .map { value ->
                value
                    .trim()
                    .toLong()
            }
        val loop = generateSequence(1L) { (it * 7L) % MODULO }
            .indexOfFirst { it == items[0] }

        return (0..<loop)
            .fold(1L) { acc, _ -> (acc * items[1]) % MODULO }
    }

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
        private const val MODULO = 20201227L
    }
}
