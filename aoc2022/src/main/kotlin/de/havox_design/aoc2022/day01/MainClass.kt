package de.havox_design.aoc2022.day01

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Top Elf has '${CaloriesCounter("de/havox_design/aoc2022/day01/day01.txt").processFile()}' calories")
            println("Top three Elves have '${CaloriesCounter("de/havox_design/aoc2022/day01/day01.txt").processFileTopThree()}' calories")
        }
    }
}
