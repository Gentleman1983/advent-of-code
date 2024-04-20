package de.havox_design.aoc2020.day08

class HandheldHalting(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        Console
            .of(data)
            .processPart1()

    fun processPart2(): Any =
        Console
            .of(data)
            .processPart2()

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
