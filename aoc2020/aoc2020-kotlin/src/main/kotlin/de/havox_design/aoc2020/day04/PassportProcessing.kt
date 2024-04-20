package de.havox_design.aoc2020.day04

class PassportProcessing(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val requiredFields = PassportFields
            .entries
            .filter { field -> field != PassportFields.COUNTRY_ID }
            .map { field -> field.key }
            .toSet()

        return splitEntries()
            .count { passport ->
            passport
                .trim()
                .split(ICON_SPACE, ICON_NEWLINE)
                .map { item -> item.substringBefore(DELIMITOR_KEY_VALUE) }
                .containsAll(requiredFields)
        }
    }

    fun processPart2(): Any =
        0L

    private fun splitEntries() =
        data
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITOR_PASSPORTS)

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val DELIMITOR_KEY_VALUE = ":"
        private const val DELIMITOR_PASSPORTS = "\n\n"
        private const val ICON_CARRIAGE_RETURN = "\r"
        private const val ICON_EMPTY = ""
        private const val ICON_NEWLINE = "\n"
        private const val ICON_SPACE = " "
    }
}
