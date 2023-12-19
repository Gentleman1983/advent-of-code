package de.havox_design.aoc2023.day19

import java.util.*

class Day19(private var filename: String) {
    private val ICON_ACCEPT = "A"
    private val ICON_REJECT = "R"
    private val ICON_START = "in"
    private val ICON_LESS_THAN = '<'
    private val ELEMENT_DELIMITER = ','
    private val RULE_START_DELIMITER = '{'
    private val SOLUTION_DELIMITER = ':'

    @SuppressWarnings("kotlin:S6611")
    fun solvePart1(): Long {
        val (rules, assignments) = readInput()

        return assignments
            .filter { assignment ->
                var current = ICON_START

                while (current != ICON_ACCEPT && current != ICON_REJECT) {
                    val rule = rules[current]!!

                    current = (rule.decisions.firstOrNull { it.test(assignment) }?.successLabel) ?: rule.elseRule
                }

                current == ICON_ACCEPT
            }
            .sumOf { it.total() }
    }

    @SuppressWarnings("kotlin:S6611")
    fun solvePart2(): Long {
        val (rules) = readInput()
        val results = mutableListOf<RangeAssignment>()
        val queue = linkedListOf(RangeAssignment() to ICON_START)

        while (queue.isNotEmpty()) {
            val (current, label) = queue.removeFirst()

            when (label) {
                ICON_ACCEPT -> {
                    results.add(current)
                    continue
                }

                ICON_REJECT -> continue
            }

            val rule = rules[label]!!
            val result = rule
                .decisions
                .runningFold(Triple(current, current, label)) { (_, falseValue), condition ->
                    falseValue
                        .split(condition)
                        .let { (a, b) -> Triple(a, b, condition.successLabel) }
                }
                .drop(1)
            result
                .map { it.first to it.third }
                .filter { it.first.isValid() }
                .toCollection(queue)
            when {
                result.last().second.isValid() -> {
                    queue.add(result.last().second to rule.elseRule)
                }
            }
        }

        return results
            .sumOf { it.sum() }
    }

    private fun <T> linkedListOf(vararg elements: T) =
        elements.toCollection(LinkedList())

    private fun readInput(): Pair<Map<String, SortRule>, List<Assignment>> {
        val input = getResourceAsText(filename)

        val rules = input
            .takeWhile { it.isNotBlank() }
            .associate { row ->
                val (name, rules) = row.split(RULE_START_DELIMITER, limit = 2)
                val splitRules = rules.split(ELEMENT_DELIMITER)
                val elseCondition = splitRules.last().dropLast(1)
                val conditions = splitRules.dropLast(1).map {
                    val (test, label) = it.split(SOLUTION_DELIMITER, limit = 2)
                    val conditionName = test.first()
                    val lessThen = test[1] == ICON_LESS_THAN
                    val value = test.drop(2).toLong()

                    Condition(conditionName, lessThen, value, label)
                }
                name to SortRule(name, conditions, elseCondition)
            }

        val regex = Regex("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}")
        val assignments = input
            .dropWhile { it.isNotBlank() }
            .drop(1)
            .map { row ->
                regex
                    .matchEntire(row)!!
                    .destructured
                    .let { (x, m, a, s) ->
                        Assignment(
                            x.toLong(),
                            m.toLong(),
                            a.toLong(),
                            s.toLong()
                        )
                    }
            }
        return rules to assignments
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}