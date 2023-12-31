package de.havox_design.aoc2023.day13

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day13.txt"

            println("Solution part 1: ${PointOfIncidence(filename).solvePart1()}")
            println("Solution part 2: ${PointOfIncidence(filename).solvePart2()}")
        }
    }
}
