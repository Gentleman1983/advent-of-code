package de.havox_design.aoc2023.day09

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "day09.txt"

            println("Solution part 1: ${MirageMaintenance(filename).solvePart1()}")
            println("Solution part 2: ${MirageMaintenance(filename).solvePart2()}")
        }
    }
}
