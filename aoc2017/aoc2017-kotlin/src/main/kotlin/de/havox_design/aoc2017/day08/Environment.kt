package de.havox_design.aoc2017.day08

data class Environment(private val variables: Map<String, Int> = emptyMap(), val processMaxValue: Int = 0) {
    val maxValue
        get() =
            variables
                .maxOf { (_, value) -> value }

    operator fun get(name: String) =
        variables[name] ?: 0

    operator fun plus(pair: Pair<String, Int>) =
        copy(
            variables = variables + pair,
            processMaxValue = maxOf(processMaxValue, pair.second),
        )
}
