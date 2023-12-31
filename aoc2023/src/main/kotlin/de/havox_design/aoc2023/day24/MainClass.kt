package de.havox_design.aoc2023.day24

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day24.txt"

            println("Solution part 1: ${NeverTellMeTheOdds(filename).solvePart1()}")
            println("Solution part 2: ${NeverTellMeTheOdds(filename).solvePart2()}")
        }
    }
}
