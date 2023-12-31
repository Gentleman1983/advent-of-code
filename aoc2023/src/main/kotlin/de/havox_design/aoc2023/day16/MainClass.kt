package de.havox_design.aoc2023.day16

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day16.txt"

            println("Solution part 1: ${TheFloorWillBeLava(filename).solvePart1()}")
            println("Solution part 2: ${TheFloorWillBeLava(filename).solvePart2()}")
        }
    }
}
