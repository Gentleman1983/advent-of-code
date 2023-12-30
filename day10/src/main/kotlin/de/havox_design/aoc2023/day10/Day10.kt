package de.havox_design.aoc2023.day10

class Day10(private var filename: String) {
    private val ICON_START = 'S'
    private val ICON_VERTICAL_PIPE = '|'
    private val ICON_HORIZONTAL_PIPE = '-'
    private val ICON_NORTH_EAST_BEND = 'L'
    private val ICON_NORTH_WEST_BEND = 'J'
    private val ICON_SOUTH_WEST_BEND = '7'
    private val ICON_SOUTH_EAST_BEND = 'F'

    fun solvePart1(): Long =
        (computeMazeLoop(
            getResourceAsText(filename)
                .map { row -> row.toCharArray().toList() }
        ).size / 2)
            .toLong()

    fun solvePart2(): Long {
        val maze = getResourceAsText(filename)
            .map { row -> row.toCharArray().toList() }

        return computeEnclosedTiles(
            computeMazeLoop(maze),
            maze
        )
    }

    @SuppressWarnings("kotlin:S3776")
    private fun computeEnclosedTiles(loop: List<Pair<Int, Int>>, maze: List<List<Char>>): Long {
        val (startIndex, newStart) = loop
            .withIndex()
            .minWith(compareBy<IndexedValue<Pair<Int, Int>>> { it.value.first }.thenBy { it.value.second })

        val counterClockwise = loop[startIndex + 1].first > newStart.first
                || loop[startIndex + 1].second < newStart.second

        val enclosedTiles = loop
            .indices
            .asSequence()
            .flatMap {
                val index = (it + startIndex) % loop.size
                val currentTile = loop[index]
                val prevTile = loop[(index + loop.size - 1) % loop.size]
                val adjacentTileDirections = setOf(
                    currentTile.getDirectionTo(prevTile),
                    currentTile.getDirectionTo(loop[(index + loop.size + 1) % loop.size])
                )

                when (adjacentTileDirections) {
                    setOf(
                        Direction.NORTH,
                        Direction.EAST
                    ) -> when {
                        !counterClockwise && prevTile.first < currentTile.first || counterClockwise && prevTile.second > currentTile.second -> {
                            left(loop, currentTile, maze).asSequence()
                        }
                        else -> {
                            emptySequence()
                        }
                    }

                    setOf(
                        Direction.SOUTH,
                        Direction.WEST
                    ) -> when {
                        !counterClockwise && prevTile.first > currentTile.first || counterClockwise && prevTile.second < currentTile.second -> {
                            right(loop, currentTile, maze).asSequence()
                        }
                        else -> {
                            emptySequence()
                        }
                    }

                    setOf(
                        Direction.SOUTH,
                        Direction.EAST
                    ) -> when {
                        !counterClockwise && prevTile.second > currentTile.second || counterClockwise && prevTile.first > currentTile.first -> {
                            left(loop, currentTile, maze).asSequence()
                        }
                        else -> {
                            emptySequence()
                        }
                    }

                    setOf(
                        Direction.NORTH,
                        Direction.WEST
                    ) -> when {
                        !counterClockwise && prevTile.second < currentTile.second || counterClockwise && prevTile.first < currentTile.first -> {
                            right(loop, currentTile, maze).asSequence()
                        }
                        else -> {
                            emptySequence()
                        }
                    }

                    setOf(Direction.NORTH, Direction.SOUTH) -> {
                        when {
                            !counterClockwise == prevTile.first < currentTile.first -> {
                                left(loop, currentTile, maze).asSequence()
                            }
                            !counterClockwise == prevTile.first > currentTile.first -> {
                                right(loop, currentTile, maze).asSequence()
                            }
                            else -> {
                                emptySequence()
                            }
                        }
                    }

                    else -> emptySequence()
                }
            }
            .toSet()

        return enclosedTiles
            .size
            .toLong()
    }

    private fun computeMazeLoop(maze: List<List<Char>>): List<Pair<Int, Int>> {
        var start: Pair<Int, Int>? = null

        out@ for (row in maze.indices) {
            for (col in maze[row].indices) {
                if (maze[row][col] == ICON_START) {
                    start = Pair(row, col)
                    break@out
                }
            }
        }

        return getLoop(start!!, maze)
    }

    private fun getLoop(start: Pair<Int, Int>, maze: List<List<Char>>): List<Pair<Int, Int>> {
        val loop = mutableListOf(start)

        do {
            val adjacentCells = maze
                .connectedCells(loop.last())
                .filter { !loop.contains(it) }
                .take(1)

            loop.addAll(adjacentCells)
        } while (adjacentCells.isNotEmpty())

        return loop
    }

    private fun List<List<Char>>.connectedCells(currentLocation: Pair<Int, Int>): List<Pair<Int, Int>> {
        return when (this[currentLocation.first][currentLocation.second]) {
            ICON_VERTICAL_PIPE -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_HORIZONTAL_PIPE -> listOf(
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.EAST)
            )

            ICON_NORTH_EAST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.EAST)
            )

            ICON_NORTH_WEST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.WEST)
            )

            ICON_SOUTH_WEST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_SOUTH_EAST_BEND -> listOf(
                currentLocation.getAdjacentField(Direction.EAST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )

            ICON_START -> listOf(
                currentLocation.getAdjacentField(Direction.NORTH),
                currentLocation.getAdjacentField(Direction.WEST),
                currentLocation.getAdjacentField(Direction.EAST),
                currentLocation.getAdjacentField(Direction.SOUTH)
            )
                .filter { (it.first in indices) && (it.second in this[it.first].indices) }
                .filter { this.connectedCells(it).any { next -> next == currentLocation } }

            else -> emptyList()
        }.filter { (it.first in indices) && (it.second in this[it.first].indices) }
    }

    private fun Pair<Int, Int>.getAdjacentField(direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(this.first - 1, this.second)
            Direction.SOUTH -> Pair(this.first + 1, this.second)
            Direction.WEST -> Pair(this.first, this.second - 1)
            Direction.EAST -> Pair(this.first, this.second + 1)
            Direction.NONE -> this
        }
    }

    @SuppressWarnings("kotlin:S6510")
    private fun left(
        loop: List<Pair<Int, Int>>,
        currentTile: Pair<Int, Int>,
        maze: List<List<Char>>
    ): List<Pair<Int, Int>> {
        val rowTiles = loop
            .filter { it.first == currentTile.first && it.second < currentTile.second }

        when {
            rowTiles.isEmpty() -> {
                return emptyList()
            }
            else -> {
                val nextTile = rowTiles
                    .maxWith(compareBy { it.second })

                return maze[currentTile.first]
                    .withIndex()
                    .filter { nextTile.second < it.index && it.index < currentTile.second }
                    .map { Pair(currentTile.first, it.index) }
            }
        }
    }

    @SuppressWarnings("kotlin:S6510")
    private fun right(
        loop: List<Pair<Int, Int>>,
        currentTile: Pair<Int, Int>,
        maze: List<List<Char>>
    ): List<Pair<Int, Int>> {
        val rowTiles = loop
            .filter { it.first == currentTile.first && it.second > currentTile.second }

        when {
            rowTiles.isEmpty() -> {
                return emptyList()
            }
            else -> {
                val nextTile = rowTiles
                    .minWith(compareBy { it.second })

                return maze[currentTile.first]
                    .withIndex()
                    .filter { currentTile.second < it.index && it.index < nextTile.second }
                    .map { Pair(currentTile.first, it.index) }
            }
        }
    }

    private fun Pair<Int, Int>.getDirectionTo(location: Pair<Int, Int>): Direction {
        when {
            this.first != location.first && this.second != location.second -> throw IllegalArgumentException()
            else -> return when {
                this.first == location.first && this.second < location.second -> return Direction.EAST
                this.first == location.first && this.second > location.second -> return Direction.WEST
                this.second == location.second && this.first < location.first -> return Direction.SOUTH
                this.second == location.second && this.first > location.first -> return Direction.NORTH
                else -> Direction.NONE
            }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}