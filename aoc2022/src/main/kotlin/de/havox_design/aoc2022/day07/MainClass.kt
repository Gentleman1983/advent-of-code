package de.havox_design.aoc2022.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Total folder size: ${NoSpaceLeftOnDevice("de/havox_design/aoc2022/day07/day07.txt").processPart1()}")
            println("Smallest folder size: ${NoSpaceLeftOnDevice("de/havox_design/aoc2022/day07/day07.txt").processPart2()}")
        }
    }
}
