package de.havox_design.aoc2015.day19

import java.util.StringJoiner
import java.util.regex.Pattern

class MedicineForRudolph(private var filename: String) {
    private val input = readData()
    private val base = parseMedicine()
    private val replacements = parseReplacements()
    private val reversedReplacements = reverseReplacements()

    fun processPart1(): Int {
        val molecules = mutableSetOf<String>()
        input.dropLast(2).forEach { line ->
            val (match, _, replace) = line.split(" ")
            molecules.addAll(allReplacements(base, match, replace))
        }
        return molecules.size
    }

    fun processPart2(): Int {
        val reversedReplacementKeySet = reversedReplacements
            .map { pair -> pair.first }
            .toSortedSet()
        var oldMolecule: String
        var molecule = base.reversed()
        val joiner = StringJoiner("|", "(", ")")
        reversedReplacementKeySet.forEach{key -> joiner.add(key)}
        val regex = Pattern.compile(joiner.toString())
        var stepCount = 0

        do {
            oldMolecule = molecule
            val matcher = regex.matcher(oldMolecule)
            val buffer = StringBuffer()
            while (matcher.find()) {
                matcher.appendReplacement(buffer, reversedReplacements.single { pair -> pair.first == matcher.group() }.second)
                stepCount++
            }
            matcher.appendTail(buffer)
            molecule = buffer.toString()
        }while (molecule != oldMolecule)

        return stepCount
    }

    private fun allReplacements(base: String, match: String, replace: String): Sequence<String> {
        val indices = match.toRegex().findAll(base).map { it.range.first }
        return indices.map { base.substring(0, it) + replace + base.substring(it + match.length) }
    }

    private fun parseMedicine() =
        input
            .dropWhile(String::isNotEmpty)
            .drop(1)
            .first()

    private fun parseReplacements() =
        input
            .takeWhile(String::isNotEmpty)
            .map { line -> line.split(" => ").let { it.first() to it.last() } }
            .asSequence()

    private fun reverseReplacements() =
        replacements
            .toList()
            .map { pair -> Pair(pair.second, pair.first) }

    private fun readData(): List<String> =
        getResourceAsText(filename)

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}