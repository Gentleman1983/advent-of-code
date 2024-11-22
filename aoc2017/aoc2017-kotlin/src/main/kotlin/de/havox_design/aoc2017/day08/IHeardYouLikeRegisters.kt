package de.havox_design.aoc2017.day08

class IHeardYouLikeRegisters(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val environment = data
        .map { it.toExpr() }
        .fold(Environment()) { environment, expression -> expression(environment) }

    fun processPart1(): Any =
        environment
            .maxValue

    fun processPart2(): Any =
        environment
            .processMaxValue

    private fun String.toExpr(): Expression {
        val (expression, predicate) = split(" if ")
        val (name, function, value) = expression.split(" ")

        return Expression(name, function, value.toInt(), Predicate.of(predicate))
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
