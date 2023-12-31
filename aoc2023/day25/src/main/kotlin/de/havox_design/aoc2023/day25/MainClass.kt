package de.havox_design.aoc2023.day25

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day25.txt"

            println("Solution part 1: ${Snowverload(filename).solvePart1()}")
            println("Solution part 2: ${Snowverload(filename).solvePart2()}")
        }
    }
}
