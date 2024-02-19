package de.havox_design.aoc2018.day18

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc2018.day17.InputLine
import de.havox_design.aoc2018.day17.getTokens
import de.havox_design.aoc2018.day17.render

class SettlersOfTheNorthPoleKotlin(private var filename: String) {

    @SuppressWarnings("kotlin:S6611")
    fun processTask1(iterations: Int): Any {
        val state: Area = getResourceAsText(filename)
            .mapIndexed { y, line ->
                InputLine(
                    line
                        .mapIndexed { x, value ->
                            Acre(Coordinate(x, y), Content.parse(value))
                        }
                        .toSet(),
                    ""
                )
            }
        val seen = mutableMapOf<Int, Int>()
        var matched: Int? = null

        while (matched == null && seen.size < iterations) {
            state.step()
            matched = seen.put(state.render().hashCode(), seen.size)
        }

        if (seen.size < iterations) {
            val current = seen[state.render().hashCode()]!!

            repeat(((iterations - matched!!) % (current - matched)) - 1) {
                state.step()
            }
        }

        return state
            .getTokens()
            .groupBy { it.content }
            .let { it[Content.TREES]!!.size * it[Content.LUMBERYARD]!!.size }
    }

    fun processTask2(): Any =
        processTask1(1000000000)

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}