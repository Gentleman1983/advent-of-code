package de.havox_design.aoc2020.day07

class Bag(private val colour: String) {
    val contains: MutableList<Pair<Bag, Int>> = mutableListOf()
    val containedIn: MutableList<Bag> = mutableListOf()

    fun containedInNames(): Set<String> =
        containedIn
            .flatMap { it.containedInNames() + it.colour }
            .toSet()

    override fun toString(): String =
        "Bag(colour='$colour')"
}
