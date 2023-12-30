package de.havox_design.aoc2024.day05

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day05.txt"

            println("Solution part 1: ${Day05(filename).solvePart1()}")
            println("Solution part 2: ${Day05(filename).solvePart2()}")
        }
    }
}
