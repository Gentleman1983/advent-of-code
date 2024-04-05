package de.havox_design.aoc2019.day18

class ManyWorldsInterpretationPart2(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart2(): Any =
        Maze
            .from(data)
            .minimumSteps()

    private fun getResourceAsText(path: String) =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
