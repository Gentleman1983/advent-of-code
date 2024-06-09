package de.havox_design.aoc2023.day15

class LensLibrary(private var filename: String) {
    private val ELEMENT_DELIMITER = ","

    fun solvePart1(): Long =
        getResourceAsText(filename)[0]
            .split(ELEMENT_DELIMITER)
            .sumOf { word -> calculateHash(word) }
            .toLong()

    fun solvePart2(): Long {
        val instructions = getResourceAsText(filename)[0]
            .split(ELEMENT_DELIMITER)
        val boxes = mutableMapOf<Int, Box>()

        for (instruction in instructions) {
            val (label, focalLength) = instruction.split('=', '-')
            val hash = calculateHash(label)

            when {
                focalLength.isBlank() -> {
                    boxes[hash]?.remove(label)
                }
                else -> {
                    boxes.computeIfAbsent(hash) { Box(it) }.add(Lens(label, focalLength.toInt()))
                }
            }
        }

        return boxes
            .filter { it.value.lenses.isNotEmpty() }
            .flatMap { it.value.lenses.mapIndexed { idx, lens -> Triple(it.key + 1, idx + 1, lens) } }
            .sumOf { it.first * it.second * it.third.focalLength }
            .toLong()
    }

    private fun calculateHash(word: String): Int =
        word
            .fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}