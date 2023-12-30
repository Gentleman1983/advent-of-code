package de.havox_design.aoc2024.day18

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day18.txt"

            println("Solution part 1: ${Day18(filename).solvePart1()}")
            println("Solution part 2: ${Day18(filename).solvePart2()}")
        }
    }
}
