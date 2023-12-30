package de.havox_design.aoc2024.day06

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day06.txt"

            println("Solution part 1: ${Day06(filename).solvePart1()}")
            println("Solution part 2: ${Day06(filename).solvePart2()}")
        }
    }
}
