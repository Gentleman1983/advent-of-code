package de.havox_design.aoc2024.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Warehouse(    val map: MapGrid<Char>,    val dimensions: Position2d<Int>,    val robot: Position2d<Int>,    val moves: List<RobotDirection>) {
    fun printMap() {
        repeat(dimensions.y) { y ->
            repeat(dimensions.x) { x ->
                print(map[Position2d(x, y)])
            }

            println()
        }
    }
}

typealias MapGrid<T> = MutableMap<Position2d<Int>, T>

@SuppressWarnings("kotlin:S1481")
fun <T> String.toMapGrid(transform: (Position2d<Int>, Char) -> T): MapGrid<T> {
    val grid = mutableMapOf<Position2d<Int>, T>()

    lines()
        .forEachIndexed { y: Int, line: String ->
        line
            .forEachIndexed { x, char ->
            val point = Position2d(x, y)

                grid[point] = transform(point, char)
        }
    }

    return grid
}