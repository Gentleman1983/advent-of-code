package de.havox_design.aoc2024.day03

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day03.txt"

            println("Solution part 1: ${Day03(filename).solvePart1()}")
            println("Solution part 2: ${Day03(filename).solvePart2()}")
        }
    }
}
