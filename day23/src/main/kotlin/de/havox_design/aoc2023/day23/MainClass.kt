package de.havox_design.aoc2023.day23

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day23.txt"

            println("Solution part 1: ${Day23(filename).solvePart1()}")
            println("Solution part 2: ${Day23(filename).solvePart2()}")
        }
    }
}
