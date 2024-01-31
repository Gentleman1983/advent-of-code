package de.havox_design.aoc2015.day18

class GIFMatrix(private var filename: String) {
    private val data = readData()

    fun processPart1(steps: Int=100): Int =
        (1..steps)
            .fold(data) { current, _ -> current.next() }
            .lights()

    fun processPart2(steps: Int=100): Int =
        (1..steps)
            .fold(data) { current, _ ->
                current.turnCornersOn().next().turnCornersOn()
            }
            .lights()

    private fun readData(): BooleanGrid =
        BooleanGrid.from(getResourceAsText(filename))

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}