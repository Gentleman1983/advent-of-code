package de.havox_design.aoc2022.day24

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputFileName = "de/havox_design/aoc2022/day24/day24.txt"

            println("Minutes needed: ${BlizzardBasin(inputFileName).processPart1()}")
            println("------------- PART 2 -----------------------------------------------")
            println("First way to goal took: ${BlizzardBasin(inputFileName).processPart2()[0]} minutes")
            println("Way back to start took: ${BlizzardBasin(inputFileName).processPart2()[1]} minutes")
            println("Way back to goal took: ${BlizzardBasin(inputFileName).processPart2()[2]} minutes")
            println("Total minutes needed: ${BlizzardBasin(inputFileName).processPart2()[3]}")
        }
    }
}
