package de.havox_design.aoc2020.day06

class CustomCustoms(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        splitCustomsForms()
            .map {
                it
                    .toCharArray()
                    .filter { char -> char in 'a'..'z' }
                    .toSet()
            }
            .sumOf { it.size }

    fun processPart2(): Any =
        splitCustomsForms()
            .map { it.lines() }
            .map {
                it
                    .fold(
                        ('a'..'z').toSet()
                    ) { acc, s -> acc.intersect(s.toSet()) }
            }
            .sumOf { it.size }

    private fun splitCustomsForms() =
        data
            .trim()
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITOR_FORMS)

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val DELIMITOR_FORMS = "\n\n"
        private const val ICON_CARRIAGE_RETURN = "\r"
        private const val ICON_EMPTY = ""
    }
}
