package de.havox_design.aoc2023.day06

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day06.txt"

            println("Solution part 1: ${WaitForIt(filename).solvePart1()}")
            println("Solution part 2: ${WaitForIt(filename).solvePart2()}")
        }
    }
}
