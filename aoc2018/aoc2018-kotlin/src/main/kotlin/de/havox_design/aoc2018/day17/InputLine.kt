package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

data class InputLine<T : InputToken>(val tokens: Set<T>, val base: String) : LineWithToken<T> {

    override fun at(index: Int) =
        base[index]

    override fun tokens() =
        tokens

    override fun base() =
        base

    override fun render(offset: Coordinate) =
        buildString {
            append(base)
            append(
                tokens
                    .sortedBy { it.point }
                    .joinToString("") { it.toString() }
            )
        }
}
