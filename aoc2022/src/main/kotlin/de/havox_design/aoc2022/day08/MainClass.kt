package de.havox_design.aoc2022.day08

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number of visible trees: ${TreetopTreeHouse("de/havox_design/aoc2022/day08/day08.txt").processPart1()}")
            println("Top scenic score: ${TreetopTreeHouse("de/havox_design/aoc2022/day08/day08.txt").processPart2()}")
        }
    }
}
