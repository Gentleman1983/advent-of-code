package de.havox_design.aoc2023.day01

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day01.txt"

            println("Solution part 1: ${Trebuchet(filename).solvePart1()}")
            println("Solution part 2: ${Trebuchet(filename).solvePart2()}")
        }
    }
}
