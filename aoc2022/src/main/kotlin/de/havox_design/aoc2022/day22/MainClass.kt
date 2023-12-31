package de.havox_design.aoc2022.day22

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Password: ${MonkeyMap("de/havox_design/aoc2022/day22/day22.txt").processPart1()}")
            println("Password: ${MonkeyMap("de/havox_design/aoc2022/day22/day22.txt").processPart2()}")
        }
    }
}
