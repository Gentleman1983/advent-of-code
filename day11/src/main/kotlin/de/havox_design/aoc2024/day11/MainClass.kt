package de.havox_design.aoc2024.day11

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day11.txt"

            println("Solution part 1: ${Day11(filename).solvePart1()}")
            println("Solution part 2: ${Day11(filename).solvePart2()}")
        }
    }
}
