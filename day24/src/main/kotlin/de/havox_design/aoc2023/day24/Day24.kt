package de.havox_design.aoc2023.day24

class Day24(private var filename: String) {
    private val ID_POSITION_X = 0
    private val ID_POSITION_Y = 1
    private val ID_POSITION_Z = 2
    private val ID_VELOCITY_X = 3
    private val ID_VELOCITY_Y = 4
    private val ID_VELOCITY_Z = 5

    fun solvePart1(minWindow: Long = 200000000000000L, maxWindow: Long = 400000000000000L): Long =
        processDay24(getResourceAsText(filename), minWindow, maxWindow)
            .first

    fun solvePart2(): Long =
        0L

    private fun processDay24(
        input: List<String>,
        minWindow: Long = 200000000000000L,
        maxWindow: Long = 400000000000000L
    ): Pair<Long, Long> {
        val windowRange = minWindow..maxWindow

        val hailstones = input
            .map { line ->
                line
                    .split(Regex("[@,]"), limit = 6)
                    .map { it.trim().toLong() }
                    .let { value ->
                        Hailstone(
                            Position3d(value[ID_POSITION_X], value[ID_POSITION_Y], value[ID_POSITION_Z]),
                            Position3d(value[ID_VELOCITY_X], value[ID_VELOCITY_Y], value[ID_VELOCITY_Z])
                        )
                    }
            }
            .toList()

        val result1 = hailstones
            .mapIndexed { index, hailstone ->
                hailstones
                    .drop(index + 1)
                    .count { hailstone.collide2D(it, windowRange) }
            }
            .sum()
            .toLong()

        return Pair(result1, 0L)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}