package de.havox_design.aoc2020.day19

sealed class Rule {

    abstract fun toRegex(rules: List<Rule>): String

    class LetterRule(val letter: String) : Rule() {
        override fun toRegex(rules: List<Rule>): String =
            letter
    }

    open class SequenceRule(val numbers: List<Int>) : Rule() {
        override fun toRegex(rules: List<Rule>): String =
            numbers
                .joinToString("") { rules[it].toRegex(rules) }
    }

    class OrRule(val sequence1: List<Int>, val sequence2: List<Int>) : Rule() {
        override fun toRegex(rules: List<Rule>): String {
            val part1 = sequence1.joinToString("") { rules[it].toRegex(rules) }
            val part2 = sequence2.joinToString("") { rules[it].toRegex(rules) }
            return "(?:$part1|$part2)"
        }
    }

    object Rule0B : Rule() {
        override fun toRegex(rules: List<Rule>): String {
            val rule42Regex = rules[42].toRegex(rules)
            val rule31Regex = rules[31].toRegex(rules)

            return "((?:$rule42Regex)+)((?:$rule31Regex)+)"
        }

        fun matches(rules: List<Rule>, message: String): Boolean {
            val matchResult = toRegex(rules).toRegex().matchEntire(message)

            val rule42Regex = rules[42].toRegex(rules)
            val rule31Regex = rules[31].toRegex(rules)

            if (matchResult != null) {
                val rule42Matches = rule42Regex.toRegex().findAll(matchResult.groups[1]!!.value)
                val rule31Matches = rule31Regex.toRegex().findAll(matchResult.groups[2]!!.value)
                return rule42Matches.count() > rule31Matches.count()
            }
            return false
        }

    }
}
