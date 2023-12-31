package de.havox_design.aoc2022.day04

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Assignments containing the other: ${CampCleanup("day04.txt").findAssignmentPairsWithOneAssignmentContainingTheOther()}")
            println("Assignments overlapping the other: ${CampCleanup("day04.txt").findAssignmentPairsWithOneAssignmentOverlappingTheOther()}")
        }
    }
}
