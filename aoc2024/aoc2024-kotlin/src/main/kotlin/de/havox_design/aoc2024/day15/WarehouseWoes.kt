package de.havox_design.aoc2024.day15

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class WarehouseWoes(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val warehouse = data

        moveRobotPart1(warehouse)

        return gpsSum(warehouse.map)
    }

    fun processPart2(): Any {
        val warehouse = expandWarehouse(data)
        moveRobotPart2(warehouse)
        return gpsSum(warehouse.map)
    }

    private fun moveRobotPart1(warehouse: Warehouse) {
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

    @SuppressWarnings("kotlin:S6611")
    private fun moveRobotPart2(warehouse: Warehouse) {
        var robot = warehouse
            .robot
        val map = warehouse
            .map

        warehouse
            .moves
            .forEach { direction ->
                val nextPosition = robot
                    .move(direction)
                when (map[nextPosition]) {
                    '.' -> {
                        map[robot] = '.'
                        robot = nextPosition
                        map[robot] = '@'
                    }

                    '[', ']' -> {
                        findMoveableBoxes(map, robot, direction)
                            ?.let { moveableBoxes ->
                                robot = nextPosition

                                val movedBoxes = moveableBoxes
                                    .map { box -> box.move(direction) to map[box]!! }
                                moveableBoxes
                                    .forEach { box -> map[box] = '.' }
                                map += movedBoxes
                                map[robot] = '@'
                            }
                    }
                }
            }
    }

    private fun findMoveableBoxes(
        map: MapGrid<Char>,
        start: Position2d<Int>,
        direction: RobotDirection
    ): List<Position2d<Int>>? {
        val nextPosition = start
            .move(direction)
        val nextObject = map[nextPosition]

        return when {
            nextObject == '.' -> listOf(start)
            (nextObject == '[' || nextObject == ']') && direction.isHorizontal() -> {
                findMoveableBoxes(map, nextPosition, direction)?.let {
                    it + start
                }
            }

            nextObject == '[' && direction.isVertical() -> {
                val left = findMoveableBoxes(map, nextPosition, direction)
                val right = findMoveableBoxes(map, nextPosition + Position2d(1, 0), direction)
                if (left == null || right == null) {
                    null
                } else {
                    left + right + start
                }
            }

            nextObject == ']' && direction.isVertical() -> {
                val right = findMoveableBoxes(map, nextPosition, direction)
                val left = findMoveableBoxes(map, nextPosition + Position2d(-1, 0), direction)
                if (left == null || right == null) {
                    null
                } else {
                    left + right + start
                }
            }

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

    private fun expandWarehouse(warehouse: Warehouse): Warehouse {
        val wideMap = mutableMapOf<Position2d<Int>, Char>()
        var robot = Position2d(0, 0)

        repeat(warehouse.dimensions.y) { y ->
            repeat(warehouse.dimensions.x) { x ->
                val wideX = 2 * x
                val char = warehouse.map[Position2d(x, y)]

                when (char) {
                    '.' -> {
                        wideMap[Position2d(wideX, y)] = '.'
                        wideMap[Position2d(wideX + 1, y)] = '.'
                    }

                    'O' -> {
                        wideMap[Position2d(wideX, y)] = '['
                        wideMap[Position2d(wideX + 1, y)] = ']'
                    }

                    '#' -> {
                        wideMap[Position2d(wideX, y)] = '#'
                        wideMap[Position2d(wideX + 1, y)] = '#'
                    }

                    '@' -> {
                        robot = Position2d(wideX, y)
                        wideMap[robot] = '@'
                        wideMap[robot + Position2d(1, 0)] = '.'
                    }
                }
            }
        }

        val dimensions = Position2d(warehouse.dimensions.x * 2, warehouse.dimensions.y)

        return Warehouse(wideMap, dimensions, robot, warehouse.moves)
    }

    private fun parseInput(input: String): Warehouse {
        val (map, moves) = input
            .split("\r\n\r\n")
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
