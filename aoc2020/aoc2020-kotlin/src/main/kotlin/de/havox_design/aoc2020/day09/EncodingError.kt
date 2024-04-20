package de.havox_design.aoc2020.day09

class EncodingError(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(preambleSize: Int = 25): Any =
        data.map { it.toLong() }
            .windowed(preambleSize + 1, partialWindows = false)
            .first { cantMakeFromParts(it) }
            .last()

    fun processPart2(): Any =
        0L

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
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
