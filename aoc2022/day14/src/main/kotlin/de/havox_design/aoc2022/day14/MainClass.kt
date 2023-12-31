package de.havox_design.aoc2022.day14

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Units of sand: ${RegolithReservoir("day14.txt").processPart1()}")
            println("Units of sand at rest: ${RegolithReservoir("day14.txt").processPart2()}")
        }
    }
}
