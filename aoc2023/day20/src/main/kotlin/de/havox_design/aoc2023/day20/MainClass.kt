package de.havox_design.aoc2023.day20

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day20.txt"

            println("Solution part 1: ${Day20(filename).solvePart1()}")
            println("Solution part 2: ${Day20(filename).solvePart2()}")
        }
    }
}
