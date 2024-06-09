package de.havox_design.aoc2020.day19

import de.havox_design.aoc.utils.kotlin.helpers.toPair

class MonsterMessages(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val (rulesList, messages) = data
            .replace(CARRIAGE_RETURN, EMPTY)
            .split(DOUBLE_NEWLINE)

        val rules = rulesList
            .lines()
            .map { it.split(COLON_SPACE).toPair() }
            .associate { (num, rule) ->
                num.toInt() to when {
                    CHAR_RULE_PATTERN.matches(rule) -> CharRule(rule.trim(LITERAL_QUOTES).first())
                    else -> RefRule(rule.split(LITERAL_OR).map { it.parseInts(SPACE) })
                }
            }

        return solve(rules, messages.lines())
    }

    fun processPart2(): Any {
        val (rulesList, messages) = data
            .replace(CARRIAGE_RETURN, EMPTY)
            .split(DOUBLE_NEWLINE)

        val rules = rulesList
            .lines()
            .map { it.split(COLON_SPACE).toPair() }
            .map { (num, rule) ->
                num to when (num) {
                    "8" -> "42 | 42 8"
                    "11" -> "42 31 | 42 11 31"
                    else -> rule
                }
            }
            .associate { (num, rule) ->
                num.toInt() to when {
                    CHAR_RULE_PATTERN.matches(rule) -> CharRule(rule.trim(LITERAL_QUOTES).first())
                    else -> RefRule(rule.split(LITERAL_OR).map { it.parseInts(SPACE) })
                }
            }

        return solve(rules, messages.lines())
    }

    private fun solve(rules: Map<Int, Rule>, messages: List<String>) =
        messages
            .count { message ->
                val cache = mutableMapOf<Triple<Int, Int, Int>, Boolean>()

                fun matches(from: Int, to: Int, ruleNum: Int): Boolean {
                    fun matches(from: Int, to: Int, rules: List<Int>): Boolean {
                        return when (rules.size) {
                            0 -> false
                            1 -> matches(from, to, rules.first())
                            else -> (from + 1..<to).any { mid ->
                                matches(from, mid, rules.first()) && matches(mid, to, rules.drop(1))
                            }
                        }
                    }

                    return cache
                        .getOrPut(Triple(from, to, ruleNum)) {
                            when (val rule = rules[ruleNum]) {
                                is CharRule -> to - from == 1 && message[from] == rule.char
                                is RefRule -> rule.refs.any { matches(from, to, it) }
                                else -> false
                            }
                        }
                }

                return@count matches(0, message.length, 0)
            }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val CARRIAGE_RETURN = "\r"
        private const val COLON_SPACE = ": "
        private const val DOUBLE_NEWLINE = "\n\n"
        private const val EMPTY = ""
        private const val LITERAL_OR = '|'
        private const val LITERAL_QUOTES = '"'
        private const val SPACE = " "

        private val CHAR_RULE_PATTERN = "\"(.)\"".toRegex()
    }
}

private fun String?.parseInts(vararg delimiters: String, radix: Int = 10): List<Int> =
    this
        ?.split(*delimiters)
        ?.mapNotNull { it.trim().toIntOrNull(radix) }
        .orEmpty()
