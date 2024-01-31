package de.havox_design.aoc2016.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Node(val position: Position2d<Int>, val size: Int, val used: Int) {
    val available = size - used
}
