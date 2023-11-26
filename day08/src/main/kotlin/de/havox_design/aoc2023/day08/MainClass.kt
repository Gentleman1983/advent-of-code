package de.havox_design.aoc2023.day08

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day08.txt"

            println("Solution part 1: ${Day08(filename).solvePart1()}")
            println("Solution part 2: ${Day08(filename).solvePart2()}")
        }
    }
}
