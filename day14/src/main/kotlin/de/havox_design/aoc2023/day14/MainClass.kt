package de.havox_design.aoc2023.day14

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day14.txt"

            println("Solution part 1: ${Day14(filename).solvePart1()}")
            println("Solution part 2: ${Day14(filename).solvePart2()}")
        }
    }
}
