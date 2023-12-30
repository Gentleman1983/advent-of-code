package de.havox_design.aoc2016.day19

import java.util.ArrayDeque

class Day19(private var filename: String) {
    fun solvePart1(): Int {
        val numberOfElves = getResourceAsText(filename)
            .map { it.toInt() }
            .first()
        val queue = ArrayDeque((1..numberOfElves).toList())

        while (queue.size > 1) {
            queue.add(queue.pop())
            queue.pop()
        }
        return queue.first
    }

    fun solvePart2(): Int {
        val numberOfElves = getResourceAsText(filename)
            .map { it.toInt() }
            .first()
        val leftNeighbors = ArrayDeque((1..(numberOfElves / 2)).toList())
        val rightNeighbors = ArrayDeque(((numberOfElves / 2) + 1..numberOfElves).toList())

        while (leftNeighbors.size + rightNeighbors.size > 1) {
            if (leftNeighbors.size > rightNeighbors.size) {
                leftNeighbors.pollLast()
            } else {
                rightNeighbors.pollFirst()
            }

            rightNeighbors.add(leftNeighbors.pollFirst())
            leftNeighbors.add(rightNeighbors.pollFirst())
        }

        return leftNeighbors.firstOrNull() ?: rightNeighbors.first()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}