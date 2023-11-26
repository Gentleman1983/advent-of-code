package de.havox_design.aoc2023.day22

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day22.txt"

            println("Solution part 1: ${Day22(filename).solvePart1()}")
            println("Solution part 2: ${Day22(filename).solvePart2()}")
        }
    }
}
