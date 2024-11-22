package de.havox_design.aoc2017.day08

enum class Operator {
    GT,
    GTE,
    LT,
    LTE,
    EQ,
    NEQ;

    companion object {
        fun of(operator: String) =
            when (operator) {
                ">" -> GT
                ">=" -> GTE
                "<" -> LT
                "<=" -> LTE
                "==" -> EQ
                "!=" -> NEQ
                else -> error("Unsupported operation '$operator'.")
            }
    }
}