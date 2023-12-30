package de.havox_design.aoc2024.day16

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day16.txt"

            println("Solution part 1: ${Day16(filename).solvePart1()}")
            println("Solution part 2: ${Day16(filename).solvePart2()}")
        }
    }
}
