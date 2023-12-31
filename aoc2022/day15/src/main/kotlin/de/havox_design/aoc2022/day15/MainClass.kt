package de.havox_design.aoc2022.day15

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number of positions: ${BeaconExclusionZone("day15.txt").processPart1(2000000)}")
            println("Tuning frequency: ${BeaconExclusionZone("day15.txt").processPart2(4000000)}")
        }
    }
}
