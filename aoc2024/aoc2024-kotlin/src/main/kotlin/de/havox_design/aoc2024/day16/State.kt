package de.havox_design.aoc2024.day16

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class State(
    val position: Position2d<Int>,
    val direction: MazeDirection,
    val score: Int,
    val path: Set<Position2d<Int>>
)
