package de.havox_design.aoc2022.day20

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Sum of grove coordinates: ${GrovePositioningSystem("de/havox_design/aoc2022/day20/day20.txt").processPart1()}")
            println("Sum of decrypted grove coordinates: ${GrovePositioningSystem("de/havox_design/aoc2022/day20/day20.txt").processPart2()}")
        }
    }
}
