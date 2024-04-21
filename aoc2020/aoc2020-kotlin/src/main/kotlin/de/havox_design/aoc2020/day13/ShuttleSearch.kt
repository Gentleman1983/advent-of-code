package de.havox_design.aoc2020.day13

class ShuttleSearch(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val start = data[ID_ARRIVAL]
            .toInt()
        val bus = data[ID_BUSSES]
            .split(DELIMITOR_BUSSES)
            .filter { it != ICON_BUSSES_OUT_OF_SERVICE }
            .map { it.toInt() }
            .minByOrNull { remainingMinutes(it, start) } ?: throw IllegalStateException()

        return bus * remainingMinutes(bus, start)
    }

    fun processPart2(): Any =
        0L

    private fun remainingMinutes(bus: Int, start: Int) =
        bus - (start % bus)

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val DELIMITOR_BUSSES = ","
        private const val ICON_BUSSES_OUT_OF_SERVICE = "x"
        private const val ID_ARRIVAL = 0
        private const val ID_BUSSES = 1
    }
}
