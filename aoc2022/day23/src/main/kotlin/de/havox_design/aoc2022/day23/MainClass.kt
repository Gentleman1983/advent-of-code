package de.havox_design.aoc2022.day23

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Empty ground tiles: ${UnstableDiffusion("day23.txt").processPart1()}")
            println("Out of possible moves in round: ${UnstableDiffusion("day23.txt").processPart2()}")
        }
    }
}
