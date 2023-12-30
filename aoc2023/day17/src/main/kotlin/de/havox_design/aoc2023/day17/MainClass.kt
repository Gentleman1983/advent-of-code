package de.havox_design.aoc2023.day17

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day17.txt"

            println("Solution part 1: ${Day17(filename).solvePart1()}")
            println("Solution part 2: ${Day17(filename).solvePart2()}")
        }
    }
}
