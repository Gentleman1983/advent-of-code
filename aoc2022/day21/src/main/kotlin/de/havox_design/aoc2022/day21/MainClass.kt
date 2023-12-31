package de.havox_design.aoc2022.day21

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number yelled by monkeys: ${MonkeyMath("day21Part1.txt").processPart1()}")
            println("Which number to yell for equality at root: ${MonkeyMath("day21Part2.txt").processPart2()}")
        }
    }
}
