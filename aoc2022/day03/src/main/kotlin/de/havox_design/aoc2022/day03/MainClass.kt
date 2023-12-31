package de.havox_design.aoc2022.day03

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Sum of priorities: ${RucksackReorganization("day03.txt").getDuplicatesScoreFromList()}")
            println("Badgesscore: ${RucksackReorganization("day03.txt").getBadgesScoreFromList()}")
        }
    }
}
