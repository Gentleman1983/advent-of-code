package de.havox_design.aoc2023.day13

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day13.txt"

            println("Solution part 1: ${Day13(filename).solvePart1()}")
            println("Solution part 2: ${Day13(filename).solvePart2()}")
        }
    }
}
