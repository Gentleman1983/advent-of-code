package de.havox_design.aoc2023.day12

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day12.txt"

            println("Solution part 1: ${HotSprings(filename).solvePart1()}")
            println("Solution part 2: ${HotSprings(filename).quicksolvePart2()}")
        }
    }
}
