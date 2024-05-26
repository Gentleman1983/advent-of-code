package de.havox_design.aoc2021.day18

class Snailfish(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        return data
            .map { line ->
                parseNumber(ArrayDeque(line.toList()))
            }
            .reduce { a, b -> a + b }
            .magnitude
    }

    fun processPart2(): Any =
        0L

    private fun parseNumber(tokens: ArrayDeque<Char>): Expression {
        return when (val token = tokens.removeFirst()) {
            in '0'..'9' -> Expression.Literal(token.digitToInt())
            '[' -> {
                val left = parseNumber(tokens)

                check(tokens.removeFirst() == ',') { "Unexpected token $token, expected \',\'" }

                val right = parseNumber(tokens)

                check(tokens.removeFirst() == ']') { "Unexpected token $token, expected \']\'" }
                Expression.Tuple(left, right)
            }
            else -> error("Unexpected token $token")
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
