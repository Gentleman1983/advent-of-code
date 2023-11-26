package de.havox_design.aoc2015.day19

class MedicineForRudolph(private var filename: String) {
    private val input = readData()
    private val base = parseMedicin()

    fun processPart1(): Int {
        val molecules = mutableSetOf<String>()
        input.dropLast(2).forEach { line ->
            val (match, _, replace) = line.split(" ")
            molecules.addAll(allReplacements(base, match, replace))
        }
        return molecules.size
    }

    fun processPart2(): Int =
        0

    private fun allReplacements(base: String, match: String, replace: String): Sequence<String> {
        val indices = match.toRegex().findAll(base).map { it.range.first }
        return indices.map { base.substring(0, it) + replace + base.substring(it + match.length) }
    }

    private fun parseMedicin() =
        input
        .dropWhile(String::isNotEmpty)
        .drop(1)
        .first()

    private fun readData(): List<String> =
        getResourceAsText(filename)

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}