package de.havox_design.aoc2022.day06

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("packet marker: ${TuningTrouble("de/havox_design/aoc2022/day06/day06.txt").processPart1()}")
            println("message marker: ${TuningTrouble("de/havox_design/aoc2022/day06/day06.txt").processPart2()}")
        }
    }
}
