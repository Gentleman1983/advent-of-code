package de.havox_design.aoc2024.day12

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day12.txt"

            println("Solution part 1: ${Day12(filename).solvePart1()}")
            println("Solution part 2: ${Day12(filename).solvePart2()}")
        }
    }
}
