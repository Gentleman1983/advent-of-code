package de.havox_design.aoc2016.day25

class Day25(private var filename: String) {
    fun solvePart1(): Int {
        val target = listOf(1, 2)
            .map { getResourceAsText(filename)[it].split(" ")[1].toInt() }
            .let { (a, b) -> a * b }

        return generateSequence(1) { answer ->
            (answer + 1)
                .takeUnless { (target + it - 1).toString(2).matches("(10)+".toRegex()) }
        }
            .last()
    }

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}