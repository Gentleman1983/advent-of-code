package de.havox_design.aoc2022.day08

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number of visible trees: ${TreetopTreeHouse("day08.txt").processPart1()}")
            println("Top scenic score: ${TreetopTreeHouse("day08.txt").processPart2()}")
        }
    }
}
