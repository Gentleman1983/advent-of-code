package de.havox_design.aoc2024.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day07.txt"

            println("Solution part 1: ${Day07(filename).solvePart1()}")
            println("Solution part 2: ${Day07(filename).solvePart2()}")
        }
    }
}
