package de.havox_design.aoc2015.day18

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${GIFMatrix("day18.txt").processPart1()}")
            println("Solution 2: ${GIFMatrix("day18.txt").processPart2()}")
        }
    }
}
