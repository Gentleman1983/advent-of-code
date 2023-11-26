package de.havox_design.aoc2023.day04

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day04.txt"

            println("Solution part 1: ${Day04(filename).solvePart1()}")
            println("Solution part 2: ${Day04(filename).solvePart2()}")
        }
    }
}
