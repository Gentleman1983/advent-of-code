package de.havox_design.aoc2022.day09

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number of visited fields: ${RopeBridge("input.txt").processPart1()}")
        }
    }
}
