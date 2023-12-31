package de.havox_design.aoc2023.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day19.txt"

            println("Solution part 1: ${Aplenty(filename).solvePart1()}")
            println("Solution part 2: ${Aplenty(filename).solvePart2()}")
        }
    }
}
