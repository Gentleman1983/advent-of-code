package de.havox_design.aoc2021.day23

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import java.util.*
import kotlin.math.abs

class Amphipod(private var filename: String) {
    private val COST_AMBER = 1
    private val COST_BRONZE = 10
    private val COST_COPPER = 100
    private val COST_DESERT = 1000

    private val ICON_SPACE = '.'

    private val ID_AMBER = 'A'
    private val ID_BRONZE = 'B'
    private val ID_COPPER = 'C'
    private val ID_DESERT = 'D'

    private val COST = mapOf(
        ID_AMBER to COST_AMBER,
        ID_BRONZE to COST_BRONZE,
        ID_COPPER to COST_COPPER,
        ID_DESERT to COST_DESERT
    )

    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        solve(data)

    fun processPart2(): Any {
        val insert = """
      |  #D#C#B#A#
      |  #D#B#A#C#
    """.trimMargin()
            .lines()

        return solve(data.take(3) + insert + data.drop(3))
    }

    private fun solve(data: List<String>): Int {
        val points = data
            .flatMapIndexed { y, row ->
                row.mapIndexedNotNull { x, c ->
                    if (c in ID_AMBER..ID_DESERT || c == ICON_SPACE) {
                        Position2d(x, y) to c
                    } else {
                        null
                    }
                }
            }
        val rawStacks = points
            .filter { it.second in ID_AMBER..ID_DESERT }
            .groupBy { it.first.x }
            .toList()
            .zip(ID_AMBER..ID_DESERT) { (x, points), char ->
                Triple(char, x, points)
            }
        val depth = rawStacks
            .first()
            .third
            .size
        val stacks = rawStacks
            .map {
                Node(
                    x = it.second,
                    char = it.first,
                )
            }
        val startStacks = stacks
            .zip(rawStacks) { stack, rawStack ->
                stack to rawStack.third.map { it.second }
            }
            .toMap()
        val entries = stacks
            .map { stack -> stack.x }
        val spots = points
            .filter { it.second == ICON_SPACE }
            .map { it.first.x }
            .filterNot { it in entries }
            .map { Node(it) }
        val exitLinks = stacks
            .associateWith { stack ->
                spots
                    .map { spot ->
                        val range = when {
                            stack.x < spot.x -> (stack.x + 1)..(spot.x - 1)
                            stack.x > spot.x -> (spot.x + 1)..(stack.x - 1)
                            else -> IntRange.EMPTY
                        }
                        Link(
                            to = spot,
                            blocking = spots.filter { it.x in range },
                            costX = abs(stack.x - spot.x),
                        )
                    }
            }
        val enterFromSpotLinks = spots
            .associateWith { spot ->
                stacks
                    .map { stack ->
                        val range = when {
                            spot.x < stack.x -> (spot.x + 1)..(stack.x - 1)
                            spot.x > stack.x -> (stack.x + 1)..(spot.x - 1)
                            else -> IntRange.EMPTY
                        }
                        Link(
                            to = stack,
                            blocking = spots.filter { it.x in range },
                            costX = abs(spot.x - stack.x),
                        )
                    }
            }
        val enterFromStackLinks = stacks
            .associateWith { fromStack ->
                stacks
                    .filterNot { it == fromStack }
                    .map { stack ->
                        val range = when {
                            fromStack.x < stack.x -> (fromStack.x + 1)..(stack.x - 1)
                            fromStack.x > stack.x -> (stack.x + 1)..(fromStack.x - 1)
                            else -> IntRange.EMPTY
                        }
                        Link(
                            to = stack,
                            blocking = spots.filter { it.x in range },
                            costX = abs(fromStack.x - stack.x),
                        )
                    }
            }
        val startWorld = World(
            spots = emptyMap(),
            stacks = startStacks,
        )
        val queue = PriorityQueue<Triple<World, Int, Int>>(1_000_000, compareBy { it.second + it.third })

        queue += Triple(startWorld, 0, Int.MAX_VALUE)

        val seen = mutableSetOf<World>()

        while (queue.isNotEmpty()) {
            val (world, score, _) = queue.remove()

            if (world in seen) {
                continue
            } else {
                seen += world
            }

            if (world.spots.isEmpty() && world.stacks.all { (stack, chars) -> chars.all { it == stack.char } }) {
                return score
            }

            val canFill = world
                .stacks
                .filter { it.value.all { char -> char == it.key.char } }
                .filter { it.value.size < depth }
            val canExit = world
                .stacks
                .filter { it.value.isNotEmpty() }
                .filter { it.key !in canFill }
                .filter { it.value.any { char -> char != it.key.char } }
            val exiting = canExit
                .flatMap { (stack, stackData) ->
                    val targets = exitLinks[stack]
                        .orEmpty()
                        .filter { it.to !in world.spots }
                        .filter { it.blocking.none { blocking -> blocking in world.spots } }
                    val costY = (depth - stackData.size + 1)

                    targets
                        .map { target ->
                            World(
                                spots = world.spots + (target.to to stackData.first()),
                                stacks = world.stacks.mapValues { (key, value) ->
                                    if (key == stack) value.drop(1) else value
                                },
                            ) to score + (target.costX + costY) * COST.getValue(stackData.first())
                        }
                }
            val enteringFromStacks = canExit
                .flatMap { (stack, stackData) ->
                    val targets = enterFromStackLinks[stack]
                        .orEmpty()
                        .filter { it.to in canFill }
                        .filter { it.to.char == stackData.first() }
                        .filter { it.blocking.none { blocking -> blocking in world.spots } }
                    val costFromY = (depth - stackData.size + 1)

                    targets
                        .map { target ->
                            val costToY = (depth - world.stacks[target.to].orEmpty().size)

                            World(
                                spots = world.spots,
                                stacks = world.stacks.mapValues { (key, value) ->
                                    when (key) {
                                        stack -> value.drop(1)
                                        target.to -> stackData.take(1) + value
                                        else -> value
                                    }
                                },
                            ) to score + (target.costX + costFromY + costToY) * COST.getValue(stackData.first())
                        }
                }
            val enteringFromSpots = world
                .spots
                .flatMap { (spot, char) ->
                    val targets = enterFromSpotLinks[spot]
                        .orEmpty()
                        .filter { it.to in canFill }
                        .filter { it.to.char == char }
                        .filter { it.blocking.none { blocking -> blocking in world.spots } }

                    targets
                        .map { target ->
                            val costToY = (depth - world.stacks[target.to].orEmpty().size)

                            World(
                                spots = world.spots - spot,
                                stacks = world.stacks.mapValues { (key, value) ->
                                    when (key) {
                                        target.to -> listOf(char) + value
                                        else -> value
                                    }
                                },
                            ) to score + (target.costX + costToY) * COST.getValue(char)
                        }
                }
            val toAdd = (exiting + enteringFromStacks + enteringFromSpots)
                .filter { it.first !in seen }
                .map { (world, score) ->
                    val spotCosts = world.spots.map { (node, char) ->
                        ((stacks.first { it.char == char }.x - node.x) + depth) * COST.getValue(char)
                    }.sum()
                    val stackCosts = world.stacks.map { (node, stack) ->
                        stack.filter { it != node.char }.sumOf { COST.getValue(it) } * 2 * depth
                    }.sum()
                    Triple(world, score, (spotCosts + stackCosts) / 4)
                }

            queue.addAll(toAdd)
        }

        return 0
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
