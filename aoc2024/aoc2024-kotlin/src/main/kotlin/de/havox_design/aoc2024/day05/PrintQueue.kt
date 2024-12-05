package de.havox_design.aoc2024.day05

import de.havox_design.aoc.utils.kotlin.helpers.parseInts

class PrintQueue(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val parsedData = parseInput()
    private val rules = parsedData
        .first
    private val updates = parsedData
        .second

    fun processPart1(): Any {
        val comparator = rules
            .comparator()

        return updates
            .filter { it.isSorted(comparator) }
            .sumOf { it[it.size / 2] }
    }

    fun processPart2(): Any {
        val comparator = rules
            .comparator()

        return updates
            .filterNot { it.isSorted(comparator) }
            .map { it.sortedWith(comparator) }
            .sumOf { it[it.size / 2] }
    }

    private fun Set<Rule>.comparator(): Comparator<Int> =
        Comparator { o1, o2 ->
            when {
                (o1 to o2) in this -> -1
                (o2 to o1) in this -> 1
                else -> error("Unknown input format! It doesn't seem to originate in Advent of Code.")
            }
        }

    private fun <T> List<T>.isSorted(comparator: Comparator<T>): Boolean {
        return asSequence()
            .windowed(2)
            .all { (o1, o2) ->
                comparator.compare(o1, o2) == -1
            }
    }

    private fun parseInput(): Pair<Set<Rule>, List<Update>> {
        val ruleRawData = ArrayList<String>()
        val updateRawData = ArrayList<String>()
        var readingRule = true

        for (entry: String in data) {
            if (readingRule) {
                if (entry.trim() == "") {
                    readingRule = false
                } else {
                    ruleRawData.add(entry.trim())
                }
            } else {
                updateRawData.add(entry.trim())
            }
        }

        val rules = ruleRawData.map { it.parseInts("|").takePair() }.toSet()
        val updates = updateRawData.map { it.parseInts(",") }

        return rules to updates
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}

private typealias Rule = Pair<Int, Int>
private typealias Update = List<Int>

private fun <T> List<T>.takePair(): Pair<T, T> =
    get(0) to get(1)
