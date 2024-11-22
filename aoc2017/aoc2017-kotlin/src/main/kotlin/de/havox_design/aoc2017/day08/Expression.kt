package de.havox_design.aoc2017.day08

data class Expression(
    private val name: String,
    private val function: String,
    private val delta: Int,
    private val predicate: Predicate,
) {
    operator fun invoke(environment: Environment) =
        if (predicate(environment)) {
            environment + (name to environment[name] + delta * if (function == "inc") 1 else -1)
        } else {
            environment
        }
}
