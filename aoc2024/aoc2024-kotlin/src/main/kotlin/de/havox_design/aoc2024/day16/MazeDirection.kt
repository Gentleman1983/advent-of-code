package de.havox_design.aoc2024.day16

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class MazeDirection(val point: Position2d<Int>) {
    UP(Position2d(0, 1)),
    RIGHT(Position2d(1, 0)),
    DOWN(Position2d(0, -1)),
    LEFT(Position2d(-1, 0));

    operator fun plus(steps: Int): MazeDirection =
        MazeDirection
            .entries[(this.ordinal + steps).mod(MazeDirection.entries.size)]

    operator fun minus(steps: Int): MazeDirection =
        this + (-1 * steps)

    fun rotateRight(steps: Int = 1): MazeDirection =
        this + steps

    fun rotateLeft(steps: Int = 1): MazeDirection =
        this - steps
}

operator fun Position2d<Int>.plus(direction: MazeDirection): Position2d<Int> =
    this + direction.point

operator fun Position2d<Int>.plus(other: Position2d<Int>): Position2d<Int> =
    Position2d(this.x + other.x, this.y + other.y)