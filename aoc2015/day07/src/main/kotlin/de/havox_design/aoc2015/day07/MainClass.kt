package de.havox_design.aoc2015.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Solution 1: ${LogicGates("day07.txt").processPart1()}")
            println("Solution 2: ${LogicGates("day07.txt").processPart2()}")
        }
    }
}
