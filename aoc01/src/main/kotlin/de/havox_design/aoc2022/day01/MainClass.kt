package de.havox_design.aoc2022.day01

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(CaloriesCounter("day01.txt").processFile())
        }
    }
}