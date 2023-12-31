package de.havox_design.aoc2023.day12

class HotSprings(private var filename: String) {
    private val DATA_DELIMITER = " "
    private val GROUPS_DELIMITER = ","
    private val ICON_BROKEN_SPRING = "#"
    private val ICON_OPERATIONAL_SPRING = "."
    private val ICON_UNKNOWN_SPRING = '?'

    // For Quicksolve
    private var springs: String = ""
    private var sizes: ArrayList<Int> = ArrayList<Int>()
    private val cache = HashMap<Pair<Int, Int>, Long>()

    fun solvePart1(): Long =
        getResourceAsText(filename)
            .map { parseInput(it) }
            .sumOf {
                it
                    .possibleGroups
                    .count()
            }

    fun solvePart2(): Long =
        getResourceAsText(filename)
            .map { parseInput(it, true) }
            .sumOf {
                it
                    .possibleGroups
                    .count()
            }

    fun quicksolvePart2(): Long {
        var sum = 0L

        for (row in getResourceAsText(filename)) {
            springs = row
                .split(DATA_DELIMITER)[0]
            val tmpSizes = ArrayList(
                row
                    .split(DATA_DELIMITER)[1]
                    .split(GROUPS_DELIMITER)
                    .map { it.toInt() }
                    .toList()
            )
            cache.clear()

            springs = "$springs?$springs?$springs?$springs?$springs"
            sizes.clear();
            for (i in 1..5) {
                sizes.addAll(tmpSizes)
            }

            sum += matches("", 0)
        }

        return sum
    }

    private fun matched(prefix: String): Boolean =
        prefix
            .filterIndexed { index, c -> c == springs[index] || springs[index] == ICON_UNKNOWN_SPRING }
            .count() == prefix.length

    @SuppressWarnings("kotlin:S6611", "kotlin:S6510")
    private fun matches(prefix: String, group: Int): Long {
        when {
            prefix.length > springs.length -> return 0
            cache.contains(Pair(prefix.length, group)) -> {
                return cache[Pair(prefix.length, group)]!!
            }

            group == sizes.size -> {
                return when {
                    matched(prefix + ICON_OPERATIONAL_SPRING.repeat(springs.length - prefix.length)) -> 1
                    else -> 0
                }
            }

            else -> {
                var result: Long = 0

                for (i in 0..<(springs.length - sizes[group])) {
                    var p = prefix;

                    when {
                        group > 0 -> p += ICON_OPERATIONAL_SPRING
                    }

                    p += ICON_OPERATIONAL_SPRING.repeat(i) + ICON_BROKEN_SPRING.repeat(sizes[group])

                    when {
                        p.length <= springs.length && matched(p) -> result += matches(p, group + 1)
                    }
                }

                cache[Pair(prefix.length, group)] = result
                return result
            }
        }
    }

    private fun parseInput(row: String, folded: Boolean = false): Record {
        val (text, groups) = row
            .split(DATA_DELIMITER)
        val groupDistribution = groups
            .split(GROUPS_DELIMITER)
            .map { it.toInt() }

        return when {
            folded -> {
                Record(
                    "$text?$text?$text?$text?$text",
                    listOf(
                        groupDistribution,
                        groupDistribution,
                        groupDistribution,
                        groupDistribution,
                        groupDistribution
                    )
                        .flatten()
                )
            }

            else -> Record(text, groupDistribution)
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}