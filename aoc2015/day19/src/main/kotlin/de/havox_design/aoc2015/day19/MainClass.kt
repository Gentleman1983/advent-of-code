package de.havox_design.aoc2015.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${MedicineForRudolph("day19.txt").processPart1()}")
            println("Solution 2 from python: ${MedicineForRudolph("day19.txt").getPythonResult("result_part2.txt")}")
        }
    }
}
