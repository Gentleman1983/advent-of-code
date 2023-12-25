package de.havox_design.aoc2024.day10

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day10.txt"

            println("Solution part 1: ${Day10(filename).solvePart1()}")
            println("Solution part 2: ${Day10(filename).solvePart2()}")
        }
    }
}
