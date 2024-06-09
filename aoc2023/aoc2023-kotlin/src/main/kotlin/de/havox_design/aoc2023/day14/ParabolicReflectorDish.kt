package de.havox_design.aoc2023.day14

class ParabolicReflectorDish(private var filename: String) {
    fun solvePart1(): Long =
        Matrix(getResourceAsText(filename))
            .rollNorth()
            .northLoad()
            .toLong()

    @SuppressWarnings("kotlin:S6611")
    fun solvePart2(numberOfCycles: Int = 1000000000): Long {
        val matrix = Matrix(getResourceAsText(filename))
        val cycleMap: MutableMap<Int, String> = mutableMapOf(0 to matrix.toString())
        var cycleIndex = 0
        var cycleLength = 0

        for (index in 1..numberOfCycles) {
            matrix.cycle()
            when {
                cycleMap.containsValue(matrix.toString()) -> {
                    cycleIndex = cycleMap
                        .filterValues { it == matrix.toString() }
                        .keys
                        .single()
                    cycleLength = index - cycleIndex
                    break
                }

                else -> {
                    cycleMap[index] = matrix.toString()
                }
            }
        }

        val pointInCycle = (numberOfCycles - cycleIndex) % cycleLength
        val actualIndex = cycleIndex + pointInCycle

        return cycleMap[actualIndex]!!
            .split("\n")
            .let(::Matrix)
            .northLoad()
            .toLong()
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}