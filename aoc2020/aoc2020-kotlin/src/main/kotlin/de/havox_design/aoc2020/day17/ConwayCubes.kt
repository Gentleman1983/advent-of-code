package de.havox_design.aoc2020.day17

class ConwayCubes(private var filename: String) {
    private val data = getResourceAsText(filename)
    lateinit var lowerBounds: IntArray
    lateinit var upperBounds: IntArray
    lateinit var hyperspace: IntTrie

    fun processPart1(): Any {
        init()
        repeat(6) { this.iterate() }

        return hyperspace.count()
    }

    fun processPart2(): Any =
        0L

    private fun init() {
        lowerBounds = intArrayOf(0, 0, 0, 0)
        upperBounds = intArrayOf(0, 0, data.lastIndex, data[0].lastIndex)
        hyperspace = IntTrie.create(lowerBounds, upperBounds)

        data
            .forEachIndexed { y, row ->
                row
                    .forEachIndexed { x, c ->
                        if (c == '#') {
                            hyperspace.add(intArrayOf(0, 0, y, x))
                        }
                    }
            }
    }

    private fun iterate() {
        lowerBounds = lowerBounds
            .mapToInt { it - 1 }
        upperBounds = upperBounds
            .mapToInt { it + 1 }
        val newHyperspace = IntTrie
            .create(lowerBounds, upperBounds)

        iterateInner(0, newHyperspace)
        hyperspace = newHyperspace
    }

    private fun iterateInner(w: Int, newHyperspace: IntTrie) {
        (lowerBounds[1]..upperBounds[1])
            .forEach { z ->
                (lowerBounds[2]..upperBounds[2])
                    .forEach { y ->
                        (lowerBounds[3]..upperBounds[3])
                            .forEach { x ->
                                val point = HyperspacePoint(intArrayOf(w, z, y, x))

                                val cell = if (hyperspace.contains(point.parts)) ACTIVE else INACTIVE
                                val count = hyperspace.countWithNeighbours(point.parts) - cell

                                if ((cell == ACTIVE && count in 2..3) || (cell == INACTIVE && count == 3)) {
                                    newHyperspace.add(point.parts)
                                }
                            }
                    }
            }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ACTIVE = 1
        private const val INACTIVE = 0
    }
}

private fun IntArray.mapToInt(transform: (Int) -> Int): IntArray =
    IntArray(this.size) { transform(this[it]) }
