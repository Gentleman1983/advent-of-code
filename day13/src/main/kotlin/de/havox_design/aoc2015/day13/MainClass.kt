package de.havox_design.aoc2015.day13

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${KnightsOfTheDinnerTable("day13.txt").processPart1()}")
            println("Solution 2: ${KnightsOfTheDinnerTable("day13.txt").processPart2()}")
        }
    }
}
