package de.havox_design.aoc2022.day05

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Top elements of all stacks are: '${SupplyStacks("input.txt").evaluateTask1()}'")
        }
    }
}