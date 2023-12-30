package de.havox_design.aoc2015.day16

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${LogicGates("day16.txt").processPart1()}")
            println("Solution 2: ${LogicGates("day16.txt").processPart2()}")
        }
    }
}
