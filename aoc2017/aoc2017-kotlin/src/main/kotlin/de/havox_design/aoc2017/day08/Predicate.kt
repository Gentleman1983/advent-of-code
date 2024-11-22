package de.havox_design.aoc2017.day08

data class Predicate(private val name: String, private val operator: Operator, private val compare: Int) {
    operator fun invoke(environment: Environment) =
        when (operator) {
            Operator.GT -> environment[name] > compare
            Operator.GTE -> environment[name] >= compare
            Operator.LT -> environment[name] < compare
            Operator.LTE -> environment[name] <= compare
            Operator.EQ -> environment[name] == compare
            Operator.NEQ -> environment[name] != compare
        }

    companion object {
        fun of(input: String): Predicate {
            val (name, operator, value) =
                input
                    .split(" ")

            return Predicate(name, Operator.of(operator), value.toInt())
        }
    }
}
