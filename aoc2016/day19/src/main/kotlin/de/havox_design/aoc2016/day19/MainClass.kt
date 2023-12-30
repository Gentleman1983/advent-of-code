package de.havox_design.aoc2016.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day19.txt"

            println("Solution part 1: ${Day19(filename).solvePart1()}")
            println("Solution part 2: ${Day19(filename).solvePart2()}")
        }
    }
}
