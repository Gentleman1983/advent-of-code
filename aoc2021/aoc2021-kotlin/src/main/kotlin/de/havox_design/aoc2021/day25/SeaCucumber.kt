package de.havox_design.aoc2021.day25

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class SeaCucumber(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        var map = Map(data.map { it.toList() })
        var step = 0

        do {
            step += 1

            val (eastCount, eastMap) = map.moveEast()
            val (southCount, southMap) = eastMap.moveSouth()

            map = southMap
        } while (eastCount + southCount > 0)

        return step
    }

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
