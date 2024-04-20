package de.havox_design.aoc2020.day05

class BinaryBoarding(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        getSeats(data)
            .max()


    fun processPart2(): Any =
        getSeats(data)
            .sorted()
            .windowed(2, 1)
            .first { (a, b) -> a + 1 != b }[0] + 1

    private fun getSeats(text: String) =
        text
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .replace(REGEX_ONE, ICON_ONE)
            .replace(REGEX_ZERO, ICON_ZERO)
            .split(ICON_NEWLINE)
            .map { it.toInt(RADIX_BINARY) }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val ICON_CARRIAGE_RETURN = "\r"
        private const val ICON_EMPTY = ""
        private const val ICON_NEWLINE = "\n"
        private const val ICON_ONE = "1"
        private const val ICON_ZERO = "0"
        private const val RADIX_BINARY = 2
        private val REGEX_ONE = "[BR]".toRegex()
        private val REGEX_ZERO = "[FL]".toRegex()
    }
}
