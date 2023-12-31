package de.havox_design.aoc2022.day18

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Surface area: ${BoilingBoulders("day18.txt").processPart1()}")
            println("Surface lava droplet: ${BoilingBoulders("day18.txt").processPart2()}")
        }
    }
}
