package de.havox_design.aoc2023.day13

class Day13(private var filename: String) {
    fun solvePart1(): Long =
        makePatterns(getResourceAsText(filename))
            .sumOf { it.mirrorValue() }
            .toLong()

    fun solvePart2(): Long =
        makePatterns(getResourceAsText(filename))
            .sumOf { it.smudgeMirrorValue() }
            .toLong()

    private fun makePatterns(input: List<String>): List<Pattern> {
        return input
            .withIndex()
            .filter { (_, line) -> line.isEmpty() }
            .map { it.index }
            .let { listOf(-1) + it + (input.lastIndex + 1) }
            .windowed(2)
            .map { (before, after) -> input.subList(before + 1, after) }
            .map(::Pattern)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}