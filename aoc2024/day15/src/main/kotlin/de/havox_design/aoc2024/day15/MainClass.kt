package de.havox_design.aoc2024.day15

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day15.txt"

            println("Solution part 1: ${Day15(filename).solvePart1()}")
            println("Solution part 2: ${Day15(filename).solvePart2()}")
        }
    }
}
