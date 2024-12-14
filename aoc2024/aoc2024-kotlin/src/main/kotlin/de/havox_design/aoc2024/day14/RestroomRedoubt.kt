package de.havox_design.aoc2024.day14

class RestroomRedoubt(private var filename: String) {
    private val data = getResourceAsText(filename)
        .map(Robot::invoke)

    fun processPart1(): Any {
        val robotCount =
            generateSequence(data) { previous ->
                previous
                    .map(Robot::move)
            }
                .drop(1)
                .take(SECONDS)
                .last()
                .groupingBy(Robot::position)
                .eachCount()

        val leftHalf = 0 until BATHROOM_WIDTH / 2
        val rightHalf = BATHROOM_WIDTH / 2 + 1 until BATHROOM_WIDTH
        val topHalf = 0 until BATHROOM_HEIGHT / 2
        val bottomHalf = BATHROOM_HEIGHT / 2 + 1 until BATHROOM_HEIGHT

        return setOf(
            leftHalf to topHalf,
            rightHalf to topHalf,
            leftHalf to bottomHalf,
            rightHalf to bottomHalf,
        )
            .map { (qx, qy) ->
                robotCount
                    .filterKeys { point ->
                        point.x in qx &&
                                point.y in qy
                    }
                    .values
                    .sum()
            }
            .reduce(Int::times)
    }

    fun processPart2(): Any =
        0L

    fun withCustomBathRoom(height: Int, width: Int): RestroomRedoubt {
        BATHROOM_HEIGHT = height
        BATHROOM_WIDTH = width

        return this
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        var BATHROOM_HEIGHT = 103
        var BATHROOM_WIDTH = 101
        private const val SECONDS = 100
    }
}
