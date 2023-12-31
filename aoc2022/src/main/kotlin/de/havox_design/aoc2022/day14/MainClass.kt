package de.havox_design.aoc2022.day14

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Units of sand: ${RegolithReservoir("de/havox_design/aoc2022/day14/day14.txt").processPart1()}")
            println("Units of sand at rest: ${RegolithReservoir("de/havox_design/aoc2022/day14/day14.txt").processPart2()}")
        }
    }
}
