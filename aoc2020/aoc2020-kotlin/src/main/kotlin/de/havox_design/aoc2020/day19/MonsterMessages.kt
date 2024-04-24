package de.havox_design.aoc2020.day19

class MonsterMessages(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val (rulesText, messages) = data
            .replace(CARRIAGE_RETURN, EMPTY)
            .split(DOUBLE_NEWLINE)
        val rules: List<Rule> = buildRules(rulesText)
        val rule0 = rules[0]
            .toRegex(rules)
            .toRegex()

        return messages
            .split(NEWLINE)
            .count { rule0.matches(it) }
    }

    fun processPart2(): Any =
        0L

    private fun buildRules(rulesText: String) =
        rulesText
            .lines()
            .map { it.split(COLON) }
            .sortedBy { it[0].toInt() }
            .mapIndexed { i, (index, rulePart) ->
                assert(index.toInt() == i)
                buildRule(rulePart.trim())
            }

    private fun buildRule(rulePart: String): Rule {
        return when {
            LITERAL_OR in rulePart -> {
                val (part1, part2) = rulePart.split(OR)

                Rule
                    .OrRule(
                        part1.trim().split(SPACE).map { it.toInt() },
                        part2.trim().split(SPACE).map { it.toInt() }
                    )
            }

            '"' in rulePart -> Rule.LetterRule(rulePart.trim(LITERAL_SPACE, LITERAL_QUOTES))
            else -> Rule.SequenceRule(rulePart.split(SPACE).map { it.toInt() })
        }
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
        private const val COLON = ":"
        private const val DOUBLE_NEWLINE = "\n\n"
        private const val EMPTY = ""
        private const val LITERAL_OR = '|'
        private const val LITERAL_QUOTES = '"'
        private const val LITERAL_SPACE = ' '
        private const val NEWLINE = "\n"
        private const val OR = "|"
        private const val SPACE = " "
    }
}
