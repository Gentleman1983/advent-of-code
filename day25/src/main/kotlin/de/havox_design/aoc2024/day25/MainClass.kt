package de.havox_design.aoc2024.day25

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day25.txt"

            println("Solution part 1: ${Day25(filename).solvePart1()}")
            println("Solution part 2: ${Day25(filename).solvePart2()}")
        }
    }
}
