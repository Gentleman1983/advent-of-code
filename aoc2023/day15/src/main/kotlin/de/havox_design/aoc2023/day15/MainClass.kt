package de.havox_design.aoc2023.day15

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day15.txt"

            println("Solution part 1: ${LensLibrary(filename).solvePart1()}")
            println("Solution part 2: ${LensLibrary(filename).solvePart2()}")
        }
    }
}
