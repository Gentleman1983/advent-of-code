package de.havox_design.aoc2022.day12

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Steps to move: ${HillClimbingAlgorithm("day12.txt").processPart1()}")
            println("Optimized steps to move: ${HillClimbingAlgorithm("day12.txt").processPart2()}")
        }
    }
}
