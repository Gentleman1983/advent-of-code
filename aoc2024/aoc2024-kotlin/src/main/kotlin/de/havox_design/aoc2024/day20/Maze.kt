package de.havox_design.aoc2024.day20

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Maze(val walls: Set<Position2d<Int>>, val start: Position2d<Int>, val end: Position2d<Int>) {
}
