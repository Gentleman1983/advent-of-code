package de.havox_design.aoc2015.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${MedicineForRudolph("day19.txt").processPart1()}")
            println("Solution 2: ${MedicineForRudolph("day19.txt").processPart2()}")
        }
    }
}
