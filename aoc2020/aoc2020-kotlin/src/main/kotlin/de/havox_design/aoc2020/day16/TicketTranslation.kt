package de.havox_design.aoc2020.day16

class TicketTranslation(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val fieldRules = extractFieldRules(data)

        val nearbyTickets = data
            .subList(data.indexOf("nearby tickets:") + 1, data.size)

        return nearbyTickets
            .asSequence()
            .flatMap { it.split(TICKET_DELIMITER).map(String::toInt) }
            .filter { value -> !fieldRules.values.any(value::let) }
            .sum()
    }

    fun processPart2(): Any =
        0L

    private fun extractFieldRules(allLines: List<String>): Map<String, (Int) -> Boolean> =
        allLines
            .takeWhile(String::isNotBlank)
            .associate {
                val key = it
                    .substringBefore(CLASS_DELIMITER)
                val (a, b, c, d) = RANGE_PATTERN
                    .matchEntire(it.substringAfter(CLASS_DELIMITER))!!
                    .destructured

                key to { value: Int -> value in (a.toInt()..b.toInt()) || value in (c.toInt()..d.toInt()) }
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val CLASS_DELIMITER = ": "
        private const val TICKET_DELIMITER = ","

        private val RANGE_PATTERN = """(\d+)-(\d+) or (\d+)-(\d+)"""
            .toRegex()
    }
}
