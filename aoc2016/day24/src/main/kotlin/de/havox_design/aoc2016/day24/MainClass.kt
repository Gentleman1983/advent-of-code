package de.havox_design.aoc2016.day24

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day24.txt"

            println("Solution part 1: ${AirDuctSpelunking(filename).solvePart1()}")
            println("Solution part 2: ${AirDuctSpelunking(filename).solvePart2()}")
        }
    }
}
