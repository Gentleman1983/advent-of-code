package de.havox_design.aoc2024.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class WarehouseWoes(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val warehouse = data

        moveRobot(warehouse)

        return gpsSum(warehouse.map)
    }

    fun processPart2(): Any =
        0L

    private fun moveRobot(warehouse: Warehouse) {
        var robot = warehouse.robot
        val map = warehouse.map

        map[robot] = '.'

        warehouse
            .moves
            .forEach { direction ->
                val nextPosition = robot.move(direction)

                when (map[nextPosition]) {
                    '.' -> robot = nextPosition
                    'O' -> {
                        firstFreeSpotInDirection(map, nextPosition, direction)
                            ?.let { freeSpot ->
                                robot = nextPosition
                                map[freeSpot] = 'O'
                                map[nextPosition] = '.'
                            }
                    }
                }
            }

        map[robot] = '@'
    }

    private fun firstFreeSpotInDirection(
        map: MapGrid<Char>,
        start: Position2d<Int>,
        direction: RobotDirection
    ): Position2d<Int>? {
        val nextPosition = start
            .move(direction)

        return when (map[nextPosition]) {
            '.' -> nextPosition
            'O' -> firstFreeSpotInDirection(map, nextPosition, direction)
            else -> null
        }
    }

    private fun gpsSum(map: MapGrid<Char>) =
        map
            .entries
            .sumOf { (point, c) ->
                if (c == 'O' || c == '[') {
                    100 * point.y + point.x
                } else {
                    0
                }
            }

    private fun parseInput(input: String): Warehouse {
        val (map, moves) = input
            .split("${System.lineSeparator()}${System.lineSeparator()}")
        val dimensions = map
            .dimensions()
        var robot = Position2d(0, 0)
        val mapGrid = map
            .toMapGrid { point, char ->
                if (char == '@') {
                    robot = point
                }
                char
            }
        val directions = moves
            .mapNotNull { RobotDirection.ofArrow(it) }

        return Warehouse(mapGrid, Position2d(dimensions.first, dimensions.second), robot, directions)
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    private fun String.dimensions(): Pair<Int, Int> =
        lines()
            .let { it.first().length to it.size }

    private fun Position2d<Int>.move(direction: RobotDirection): Position2d<Int> =
        this + direction.delta

    private operator fun Position2d<Int>.plus(other: Position2d<Int>): Position2d<Int> =
        Position2d(x + other.x, y + other.y)
}
