package de.havox_design.aoc2020.day16

import de.havox_design.aoc.utils.kotlin.helpers.math.product

class TicketTranslation(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val fieldRules = extractFieldRules(data)

        val nearbyTickets = data
            .subList(data.indexOf(TICKET_IDENTIFIER_NEARBY) + 1, data.size)

        return nearbyTickets
            .asSequence()
            .flatMap { it.split(TICKET_DELIMITER).map(String::toInt) }
            .filter { value -> !fieldRules.values.any(value::let) }
            .sum()
    }

    @SuppressWarnings("kotlin:S6611")
    fun processPart2(): Any {
        val fieldRules = extractFieldRules(data)

        val yourTicket = data[data.indexOf(TICKET_IDENTIFIER_YOU) + 1]
            .split(TICKET_DELIMITER)
            .map(String::toInt)

        val possibleFieldNames = Array(yourTicket.size) { fieldRules.keys.toMutableSet() }
        val solvedFields = Array<String?>(yourTicket.size) { null }

        data.subList(data.indexOf(TICKET_IDENTIFIER_NEARBY) + 1, data.size)
            .map { it.split(TICKET_DELIMITER).map(String::toInt) }
            .filter { numbers -> !containsInvalidFields(numbers, fieldRules) }
            .forEach { ticket ->
                ticket
                    .forEachIndexed { i, value ->
                        possibleFieldNames[i].removeIf { rule -> !fieldRules[rule]!!(value) }
                    }
            }

        while (solvedFields.any { it == null }) {
            fieldRules
                .keys
                .filter { it !in solvedFields }
                .forEach { name ->
                    val possibleIndices = possibleFieldNames
                        .withIndex()
                        .filter { (_, it) -> it.contains(name) }

                    if (possibleIndices.size == 1) {
                        val (index, _:Set<String>) = possibleIndices.first()
                        solvedFields[index] = name
                        possibleFieldNames[index].clear()
                        possibleFieldNames.forEach { it.remove(name) }
                    }
                }

            possibleFieldNames
                .withIndex()
                .filter { (_, names:Set<String>) -> names.size == 1 }
                .forEach { (index, names:Set<String>) ->
                    val name = names.first()
                    solvedFields[index] = name
                    possibleFieldNames[index].clear()
                    possibleFieldNames.forEach { it.remove(name) }
                }
        }

        return yourTicket
            .mapIndexed { i, value -> solvedFields[i]!! to value }
            .filter { (name, _) -> name.startsWith(KEYWORD) }
            .map { (_, value) -> value.toLong() }
            .product()
    }

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

    private fun containsInvalidFields(numbers: List<Int>, fieldRules: Map<String, (Int) -> Boolean>) =
        numbers
            .any { value -> !fieldRules.values.any(value::let) }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val CLASS_DELIMITER = ": "
        private const val KEYWORD = "departure"
        private const val TICKET_DELIMITER = ","
        private const val TICKET_IDENTIFIER_NEARBY = "nearby tickets:"
        private const val TICKET_IDENTIFIER_YOU = "your ticket:"

        private val RANGE_PATTERN = """(\d+)-(\d+) or (\d+)-(\d+)"""
            .toRegex()
    }
}
