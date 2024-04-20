package de.havox_design.aoc2020.day11

class SeatingSystem(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        Seats(
            data
                .map { it.toCharArray() }
                .toTypedArray()
        )
            .simulateUntilStable(Seats::calculateOccupiedSeats)
            .count(ICON_OCCUPIED_SEAT)

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        const val ICON_EMPTY_SEAT = 'L'
        const val ICON_FLOOR = '.'
        const val ICON_OCCUPIED_SEAT = '#'
    }
}
