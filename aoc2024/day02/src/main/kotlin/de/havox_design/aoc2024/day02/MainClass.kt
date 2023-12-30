package de.havox_design.aoc2024.day02

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day02.txt"

            println("Solution part 1: ${Day02(filename).solvePart1()}")
            println("Solution part 2: ${Day02(filename).solvePart2()}")
        }
    }
}