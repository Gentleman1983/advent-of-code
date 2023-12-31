package de.havox_design.aoc2023.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day07.txt"

            println("Solution part 1: ${CamelCards(filename).solvePart1()}")
            println("Solution part 2: ${CamelCards(filename).solvePart2()}")
        }
    }
}
