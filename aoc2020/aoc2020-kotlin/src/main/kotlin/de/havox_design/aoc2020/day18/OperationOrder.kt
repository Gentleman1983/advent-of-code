package de.havox_design.aoc2020.day18

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class OperationOrder(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .map { it.toCharArray().filter { char -> char != SPACE } }
            .map { buildPostfix(it, mapOf(LITERAL_MULTIPLY to 1, LITERAL_PLUS to 1)) }
            .sumOf { evaluatePostfix(it) }

    fun processPart2(): Any =
        0L

    @SuppressWarnings("kotlin:S6611")
    private fun buildPostfix(tokens: List<Char>, operatorPrecedence: Map<Char, Int>): Queue<Char> {
        val output: Queue<Char> = LinkedList()
        val operators: Stack<Char> = Stack()

        tokens
            .forEach { token ->
                when (token) {
                    in LITERAL_ZERO..LITERAL_NINE -> output.add(token)
                    LITERAL_BRACKET_OPEN -> operators.add(token)
                    LITERAL_PLUS, LITERAL_MULTIPLY -> {
                        while (operators.isNotEmpty()
                            && operators.peek() != LITERAL_BRACKET_OPEN
                            && operatorPrecedence[operators.peek()]!! >= operatorPrecedence[token]!!
                        ) {
                            output.add(operators.pop())
                        }
                        operators.push(token)
                    }

                    LITERAL_BRACKET_CLOSE -> {
                        while (operators.peek() != LITERAL_BRACKET_OPEN) {
                            output.add(operators.pop())
                        }
                        operators.pop()
                    }
                }
            }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }

        return output
    }

    private fun evaluatePostfix(postfix: Queue<Char>): Long {
        val numbers = Stack<Long>()

        postfix
            .forEach { token ->
                when (token) {
                    in LITERAL_ZERO..LITERAL_NINE -> numbers.push(token.toString().toLong())
                    LITERAL_PLUS -> numbers.push(numbers.pop() + numbers.pop())
                    LITERAL_MULTIPLY -> numbers.push(numbers.pop() * numbers.pop())
                }
            }

        return numbers
            .pop()
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val LITERAL_BRACKET_CLOSE = ')'
        private const val LITERAL_BRACKET_OPEN = '('
        private const val LITERAL_MULTIPLY = '*'
        private const val LITERAL_NINE = '9'
        private const val LITERAL_PLUS = '+'
        private const val LITERAL_ZERO = '0'
        private const val SPACE = ' '
    }
}
