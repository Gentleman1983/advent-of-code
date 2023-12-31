package de.havox_design.aoc2022.day13

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Sum of indices with right order: ${DistressSignal("day13.txt").processPart1()}")
            println("Decoder key: ${DistressSignal("day13.txt").processPart2()}")
        }
    }
}
