package de.havox_design.aoc2023.day05

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day05.txt"

            println("Solution part 1: ${Day05(filename).solvePart1()}")
            println("Calculation of solution 2 may take some minutes...") // 9min 15sec on Ryzen 5700G
            println("Solution part 2: ${Day05(filename).solvePart2()}")
        }
    }
}
