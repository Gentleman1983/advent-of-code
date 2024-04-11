package de.havox_design.aoc2019.day25

class Cryostasis(private var filename: String) {
    private val data = getResourceAsLongList(filename)

    fun processPart1(): Any =
        ShipExplorer(data)
            .solveA()
    
    fun processPart2(): Any =
        "Merry X-Mas!!!"

    private fun getResourceAsLongList(path: String) =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
            .trim()
            .split(",")
            .map(String::toLong)
}
