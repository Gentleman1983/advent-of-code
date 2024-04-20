package de.havox_design.aoc2020.day07

class Graph {
    private val bags: MutableMap<String, Bag> = mutableMapOf()

    operator fun get(bag: String) =
        bags[bag] ?: throw IllegalArgumentException("No item")

    fun addRule(rule: String) {
        val (colour, containsRule) = REGEX_COLOUR
            .matchEntire(rule)!!
            .destructured
        val bag = getOrPut(colour)

        if (containsRule != "no other bags") {
            val parts = containsRule.split(DELIMITER_PARTS)

            parts
                .forEach {
                val (count, innerColour) = REGEX_CONTAINS
                    .matchEntire(it.trim())!!
                    .destructured
                val innerBag = getOrPut(innerColour)

                innerBag.containedIn.add(bag)
                bag.contains.add(Pair(innerBag, count.toInt()))
            }
        }
    }

    private fun getOrPut(colour: String) =
        bags
            .getOrPut(colour) { Bag(colour) }

    companion object {
        private const val DELIMITER_PARTS = ","
        private val REGEX_COLOUR = """^([A-Za-z ]+) bags contain (.*)\.$""".toRegex()
        private val REGEX_CONTAINS = """^(\d+) ([A-Za-z ]+) bags?$""".toRegex()
    }
}