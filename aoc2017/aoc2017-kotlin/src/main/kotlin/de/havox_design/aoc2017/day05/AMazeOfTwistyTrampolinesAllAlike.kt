package de.havox_design.aoc2017.day05

class AMazeOfTwistyTrampolinesAllAlike(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val numbers = data
        .map { it.toInt() }
    private val indices = numbers
        .indices

    fun processPart1(): Any =
        solve()

    fun processPart2(): Any =
        solve(true)

    @SuppressWarnings("kotlin:S6524")
    private fun solve(decrease: Boolean = false): Int {
        val items = numbers
            .toMutableList()
        var index = 0
        var steps = 0

        do {
            steps++
            index += if (items[index] >= 3 && decrease) {
                items[index]--
            } else {
                items[index]++
            }
        } while (index in indices)

        return steps
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
