package de.havox_design.aoc2022.day04

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Assignments containing the other: ${CampCleanup("de/havox_design/aoc2022/day04/day04.txt").findAssignmentPairsWithOneAssignmentContainingTheOther()}")
            println("Assignments overlapping the other: ${CampCleanup("de/havox_design/aoc2022/day04/day04.txt").findAssignmentPairsWithOneAssignmentOverlappingTheOther()}")
        }
    }
}
