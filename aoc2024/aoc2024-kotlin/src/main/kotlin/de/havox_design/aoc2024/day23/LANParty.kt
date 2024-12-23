package de.havox_design.aoc2024.day23

class LANParty(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val triangles = HashSet<List<String>>()

        for ((from, neighbours) in data) {
            if (from.startsWith(CHIEF_HISTORIAN_IDENTIFIER)) {
                for (u in neighbours) {
                    for (v in neighbours) {
                        if (data[v]?.contains(u) == true) {
                            triangles += listOf(from, u, v)
                                .sorted()
                        }
                    }
                }
            }
        }

        return triangles
            .size
    }

    fun processPart2(): Any {
        val result = mutableListOf<String>()

        data
            .findClique(result)
        result
            .sortBy { it.length }

        return result
            .last()
    }

    @SuppressWarnings("kotlin:S6611")
    private fun HashMap<String, MutableSet<String>>.findClique(
        result: MutableList<String>,
        r: Set<String> = mutableSetOf(),
        p: MutableSet<String> = keys
            .toMutableSet(),
        x: MutableSet<String> = mutableSetOf()
    ) {
        if (p.isEmpty() && x.isEmpty()) {
            result += r
                .toList()
                .sorted()
                .joinToString(ELEMENT_SEPARATOR)
        } else {
            val pivot = (p union x)
                .maxByOrNull { this[it]?.size ?: 0 } ?: throw IllegalStateException("This should never have happened!")

            for (v in p - this[pivot]!!) {
                findClique(
                    result, r union setOf(v), (p intersect this[v]!!).toMutableSet(), (x intersect this[v]!!)
                        .toMutableSet()
                )
                p
                    .remove(v)
                x
                    .add(v)
            }
        }
    }

    private fun parseInput(input: List<String>) =
        input
            .map { row ->
                row
                    .split(COMPUTER_DIVIDER)
                    .let { connection ->
                        connection.first() to connection.last()
                    }
            }
            .toGraph()

    private fun List<Pair<String, String>>.toGraph(): HashMap<String, MutableSet<String>> {
        val graph = HashMap<String, MutableSet<String>>()

        for ((from, to) in this) {
            graph
                .add(from, to)
            graph
                .add(to, from)
        }

        return graph
    }

    private fun HashMap<String, MutableSet<String>>.add(from: String, to: String) {
        getOrPut(from) { mutableSetOf() } += to
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val CHIEF_HISTORIAN_IDENTIFIER = 't'
        private const val COMPUTER_DIVIDER = "-"
        private const val ELEMENT_SEPARATOR = ","
    }
}
