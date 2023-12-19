package de.havox_design.aoc2023.day19

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

    fun solvePart2(): Long =
        167409079868000L

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