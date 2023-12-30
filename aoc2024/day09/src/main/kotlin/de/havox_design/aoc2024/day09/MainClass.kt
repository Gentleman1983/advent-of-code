package de.havox_design.aoc2024.day09

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day09.txt"

            println("Solution part 1: ${Day09(filename).solvePart1()}")
            println("Solution part 2: ${Day09(filename).solvePart2()}")
        }
    }
}
