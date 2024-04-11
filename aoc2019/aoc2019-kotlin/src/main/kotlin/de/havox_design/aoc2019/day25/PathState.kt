package de.havox_design.aoc2019.day25

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection
import java.util.Stack

class PathState {
    var lastItem: String? = null
    var currentRoom: Room? = null
    val currentPath = Stack<GeoDirection>()
    val commandsHistory = mutableListOf<String>()
}
