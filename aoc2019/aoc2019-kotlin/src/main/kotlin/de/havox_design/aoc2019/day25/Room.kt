package de.havox_design.aoc2019.day25

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection

class Room(val name: String, private val doors: Set<GeoDirection>) {
    val neighbours = mutableMapOf<GeoDirection, Room>()

    fun hasUnknownNeighbours() =
        doors
            .any { neighbours[it] == null }

    fun unknownNeighbours() =
        doors
            .filter { neighbours[it] == null }
}
