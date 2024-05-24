package de.havox_design.aoc2021.day10

import java.util.*

class SyntaxScoring(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val pairs = mapOf(
        '(' to ')',
        '<' to '>',
        '{' to '}',
        '[' to ']'
    )
    private val opening = setOf('(', '{', '<', '[')
    private val closing = setOf(')', '}', '>', ']')
    private val syntaxErrorScore = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )
    private val autocompletionScore = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    fun processPart1(): Any =
        data
            .mapNotNull { firstIllegalChar(it) }
            .sumOf { syntaxErrorScore[it] ?: 0 }

    fun processPart2(): Any {
        val allScores = data.mapNotNull { completeLine(it) }
            .map { calcPoints(it) }
            .sorted()

        return allScores[allScores.size / 2]
    }

    private fun firstIllegalChar(line: String): Char? {
        val stack = Stack<Char>()

        for (it in line) {
            if (it in opening) {
                stack.push(it)
            } else if (it in closing) {
                val lastOpen = stack.pop()
                if (it != pairs[lastOpen]) {
                    return it
                }
            }
        }
        return null
    }

    private fun completeLine(line: String): List<Char>? {
        val stack = Stack<Char>()

        for (it in line) {
            if (it in opening) {
                stack.push(it)
            } else if (it in closing) {
                val lastOpen = stack.pop()
                if (it != pairs[lastOpen]) {
                    return null
                }
            }
        }
        return stack
            .mapNotNull { pairs[it] }
    }

    private fun calcPoints(it: List<Char>): Long =
        it
            .foldRight(0) { c, acc -> acc * 5 + (autocompletionScore[c] ?: 0) }


    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
