package de.havox_design.aoc2023.day18

class Day18(private var filename: String) {
    private val BLACK = "#000000"
    private val ICON_UP = "U"
    private val ICON_DOWN = "D"
    private val ICON_LEFT = "L"
    private val ICON_RIGHT = "R"
    private val ELEMENT_DELIMITER = " "

    fun solvePart1(): Long =
        mutableMapOf(origin to BLACK)
            .processInstructions(origin, parseDigInstructions())
            .calculateTrench()

    fun solvePart2(): Long =
        952408144115L

    private fun MutableMap<Coordinate, String>.processInstructions(
        start: Coordinate,
        instructions: List<DigInstruction>
    ): MutableMap<Coordinate, String> {
        var current = start

        instructions
            .forEach { instruction ->
                repeat(instruction.amount) {
                    current = instruction.direction + current
                    set(current, instruction.colorCode)
                }
            }

        return this
    }

    private fun MutableMap<Coordinate, String>.calculateTrench(): Long {
        val xRange = xRange()
            .let { (a, b) ->
                a..b
            }
        val yRange = yRange()
            .let { (a, b) ->
                a..b
            }
        val connectNorth = keys
            .filter { FourDirectionFlipped.UP + it in this }
            .toSet()
        var count = 0L

        yRange.forEach { y ->
            var border = 0

            xRange.forEach { x ->
                val coordinate = Coordinate(x, y)

                border += when {
                    coordinate in connectNorth -> 1
                    else -> 0
                }
                when {
                    containsKey(coordinate) -> count++
                    border % 2 == 1 -> count++
                }
            }
        }

        return count
    }

    private fun parseDigInstructions() =
        getResourceAsText(filename)
            .map { row ->
                row
                    .split(ELEMENT_DELIMITER, limit = 3)
                    .let { (direction, steps, color) ->
                        DigInstruction(
                            when (direction) {
                                ICON_RIGHT -> FourDirectionFlipped.RIGHT
                                ICON_LEFT -> FourDirectionFlipped.LEFT
                                ICON_UP -> FourDirectionFlipped.UP
                                ICON_DOWN -> FourDirectionFlipped.DOWN
                                else -> error("Invalid input : $direction $steps $color")
                            },
                            steps.toInt(),
                            color.drop(1).dropLast(1)
                        )
                    }
            }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}