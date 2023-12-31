package de.havox_design.aoc2022.day02

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Score of Guide: ${RockPaperScissorsGame("day02.txt").getResultForGuide()}")
            println("Score by expected result: ${RockPaperScissorsGame("day02.txt").getResultForExpectedResult()}")
        }
    }
}
