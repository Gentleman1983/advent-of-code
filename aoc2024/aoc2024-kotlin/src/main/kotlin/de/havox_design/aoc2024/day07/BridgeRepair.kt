package de.havox_design.aoc2024.day07

import de.havox_design.aoc.utils.kotlin.helpers.math.longs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class BridgeRepair(private var filename: String) {
    private val data = parseData(getResourceAsText(filename))

    fun processPart1(): Any =
        calculateValidEquations(data, listOf(ADD, MULTIPLY))

    fun processPart2(): Any =
        calculateValidEquations(data, listOf(ADD, CONCATENATE, MULTIPLY))

    @SuppressWarnings("kotlin:S6528")
    private fun calculateValidEquations(equations: List<Pair<Long, List<Long>>>, validOperators: List<String>): Long {
        val allPossibleOperations = equations
            .map { (_, equation) -> equation.size - 1 }
            .distinct()
            .associateWith { numberOperators ->
                possibleOperations(validOperators, numberOperators)
                    .map { it.split("") }
            }
        return equations
            .productMap { (result, equation) ->
                val valid = allPossibleOperations[equation.size - 1]
                    ?.firstOrNull {
                        equation
                            .reduceIndexed { index, acc, number ->
                                when (it[index]) {
                                    ADD -> acc + number
                                    MULTIPLY -> acc * number
                                    else -> "$acc$number".toLong()
                                }
                            } == result
                    } != null

                if (valid) {
                    result
                } else {
                    0L
                }
            }
            .sum()
    }

    private fun possibleOperations(characters: List<String>, length: Int, s: String = ""): List<String> =
        if (length == s.length) {
            listOf(s)
        } else {
            characters.flatMap { possibleOperations(characters, length, "$s$it") }
        }

    @SuppressWarnings("kotlin:S6310", "kotlin:S6311")
    private fun <A, B> List<A>.productMap(f: suspend (A) -> B): List<B> =
        runBlocking {
            map { async(Dispatchers.Default) { f(it) } }
                .awaitAll()
        }

    private fun parseData(input: List<String>): List<Pair<Long, List<Long>>> =
        input.map { row ->
            val numbers = row.longs()
            numbers.first() to numbers.drop(1)
        }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ADD = "+"
        private const val CONCATENATE = "|"
        private const val MULTIPLY = "*"
    }
}
