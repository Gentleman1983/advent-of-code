package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

interface LineWithToken<T : InputToken> {

    fun tokens(): Set<T>

    fun base(): String

    fun at(index: Int): Char

    fun render(offset: Coordinate = Coordinate(0, 0)): String
}
