package de.havox_design.aoc2021.day04

class Board(private val cells: Map<Int, BingoCell>) {
    private var lastMarked: Int = 0
    private val rowMarkCount = mutableMapOf<Int, Int>()
    private val columnMarkCount = mutableMapOf<Int, Int>()

    fun hasWon(): Boolean =
        rowMarkCount.any { it.value == 5 } || columnMarkCount.any { it.value == 5 }

    fun mark(number: Int) {
        cells[number]
            ?.also {
                it.marked = true
                lastMarked = number
                rowMarkCount[it.y] = rowMarkCount.getOrDefault(it.y, 0) + 1
                columnMarkCount[it.x] = columnMarkCount.getOrDefault(it.x, 0) + 1
            }
    }

    fun score(): Int =
        cells
            .entries
            .filter { (_, value) -> !value.marked }
            .sumOf { (key, _) -> key } * lastMarked
}
