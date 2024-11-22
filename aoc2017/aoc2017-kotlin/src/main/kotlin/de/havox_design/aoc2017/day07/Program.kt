package de.havox_design.aoc2017.day07

data class Program(val name: String, val weight: Int, val children: List<String>) {
    val leaf
        get() = children
            .isEmpty()
}
