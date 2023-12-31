package de.havox_design.aoc2023.day03

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day03.txt"

            println("Solution part 1: ${GearRatios(filename).solvePart1()}")
            println("Solution part 2: ${GearRatios(filename).solvePart2()}")
        }
    }
}
