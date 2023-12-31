package de.havox_design.aoc2023.day22

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day22.txt"

            println("Solution part 1: ${SandSlabs(filename).solvePart1()}")
            println("Solution part 2: ${SandSlabs(filename).solvePart2()}")
        }
    }
}
