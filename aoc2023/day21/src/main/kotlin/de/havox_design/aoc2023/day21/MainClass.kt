package de.havox_design.aoc2023.day21

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day21.txt"

            println("Solution part 1: ${StepCounter(filename).solvePart1()}")
            println("Solution part 2: ${StepCounter(filename).solvePart2()}")
        }
    }
}
