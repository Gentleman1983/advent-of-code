package de.havox_design.aoc2023.day11

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day11.txt"

            println("Solution part 1: ${CosmicExpansion(filename).solvePart1()}")
            println("Solution part 2: ${CosmicExpansion(filename).solvePart2()}")
        }
    }
}
