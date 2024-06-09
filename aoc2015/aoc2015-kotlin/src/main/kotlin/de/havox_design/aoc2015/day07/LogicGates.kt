package de.havox_design.aoc2015.day07

import kotlin.collections.set

class LogicGates(private var filename: String) {
    private val data = readData()

    fun processPart1(): Int =
        calculate(data, mutableMapOf(), "a")

    fun processPart2(): Int =
        calculate(data, mutableMapOf("b" to processPart1()), "a")

    @SuppressWarnings("kotlin:S6611")
    private fun calculate(
        calculationRules: MutableMap<String, List<String>>,
        variableValues: MutableMap<String, Int>,
        variableName: String
    ): Int {
        val regex = "\\d+".toRegex()
        if (variableName.matches(regex))
            return variableName.toInt()

        val compute: (String) -> Int = (::calculate).partial(calculationRules, variableValues)

        if (variableName !in variableValues) {
            val operands = calculationRules[variableName]!!
            val result = if (operands.size == 1) {
                compute(operands[0])
            } else {
                when (operands[operands.size - 2]) {
                    "AND" -> compute(operands[0]) and compute(operands[2])
                    "OR" -> compute(operands[0]) or compute(operands[2])
                    "NOT" -> compute(operands[1]).inv() and 0xffff
                    "RSHIFT" -> compute(operands[0]) shr compute(operands[2])
                    "LSHIFT" -> compute(operands[0]) shl compute(operands[2])
                    else -> 0
                }
            }
            variableValues[variableName] = result
        }
        return variableValues[variableName]!!
    }

    private fun readData(): MutableMap<String, List<String>> {
        val fileData = mutableMapOf<String, List<String>>()

        getResourceAsText(filename).forEach { command: String ->
            val (operands, result) = command.split(" -> ").filter { it != "" }
            fileData[result.trim()] = operands.trim().split(" ").filter { it != "" }
        }

        return fileData
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

fun <A, B, C, D> Function3<A, B, C, D>.partial(a: A, b: B): (C) -> D {
    return { c -> invoke(a, b, c) }
}
