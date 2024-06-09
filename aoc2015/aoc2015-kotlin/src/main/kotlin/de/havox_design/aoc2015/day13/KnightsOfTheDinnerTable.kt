package de.havox_design.aoc2015.day13


class KnightsOfTheDinnerTable(private var filename: String) {
    private val data = readData()

    fun processPart1(): Int =
        data
            .guests()
            .arrangements()
            .maxOf { arrangement -> arrangement.totalHappiness(data) }

    fun processPart2(): Int =
        data
            .toMutableMap()
            .apply {
                data
                    .guests()
                    .forEach { guest ->
                        this[Guest("Me") to guest] = 0
                        this[guest to Guest("Me")] = 0
                    }
            }
            .let {
                it
                    .guests()
                    .arrangements()
                    .maxOf { arrangement -> arrangement.totalHappiness(it) }
            }

    private fun readData(): Map<Pair<Guest, Guest>, Int> {
        val fileData = getResourceAsText(filename)
            .associate { line ->
                val (guest, gainLose, happiness, neighbor) = STATEMENT_PATTERN.matchEntire(line)!!.destructured
                Pair(Guest(guest), Guest(neighbor)) to if (gainLose == "gain") happiness.toInt() else -happiness.toInt()
            }
            .toMap()

        return fileData
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    private fun Map<Pair<Guest, Guest>, Int>.guests() =
        keys
            .map { it.first }
            .toSet()

    private fun <T> Collection<T>.arrangements(): Set<List<T>> {
        if (size <= 3) {
            return setOf(this.toList())
        }

        val result: MutableSet<List<T>> = mutableSetOf()
        val first = first()

        this
            .drop(1)
            .arrangements()
            .forEach { list ->
                for (index in list.indices) {
                    val new = list.toMutableList()

                    new.add(index, first)
                    result.add(new)
                }
            }

        return result
    }

    @SuppressWarnings("kotlin:S6611")
    private fun List<Guest>.totalHappiness(mutableMap: Map<Pair<Guest, Guest>, Int>): Int {
        return this.sumOf { person ->
            val leftNeighbor = this[(indexOf(person) - 1 + size) % size]
            val rightNeighbor = this[(indexOf(person) + 1) % size]

            mutableMap[person to leftNeighbor]!! + mutableMap[person to rightNeighbor]!!
        }
    }

    companion object {
        private val STATEMENT_PATTERN =
            Regex("""^(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).$""")
    }
}
