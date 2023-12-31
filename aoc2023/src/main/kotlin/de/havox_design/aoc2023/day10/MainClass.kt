package de.havox_design.aoc2023.day10

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day10.txt"

            println("Solution part 1: ${PipeMaze(filename).solvePart1()}")
            println("Solution part 2: ${PipeMaze(filename).solvePart2()}")
        }
    }
}
