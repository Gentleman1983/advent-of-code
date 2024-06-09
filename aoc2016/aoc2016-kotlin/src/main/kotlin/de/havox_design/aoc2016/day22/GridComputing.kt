package de.havox_design.aoc2016.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import kotlin.math.abs

class GridComputing(private var filename: String) {
    fun solvePart1(): Int {
        val nodes = getResourceAsText(filename)
            .drop(2)
            .mapNotNull { parse(it) }

        return nodes
            .flatMap { node ->
                nodes.mapNotNull { other ->
                    setOf(node, other).takeIf { node != other && node.used > 0 && node.used <= other.available }
                }
            }
            .toSet()
            .size
    }

    fun solvePart2(): Int {
        val nodes = getResourceAsText(filename)
            .drop(2)
            .mapNotNull { parse(it) }
            .sortedByDescending { it.available }
        val maxX = nodes.maxOf { it.position.x }
        val wall = nodes.filter { it.size > 250 }.minByOrNull { it.position.x }!!
        val emptyNode = nodes.first { it.used == 0 }
        var result = abs(emptyNode.position.x - wall.position.x) + 1

        result += emptyNode.position.y
        result += maxX - wall.position.x

        return result + (5 * (maxX - 1)) + 1
    }

    private fun parse(line: String): Node? =
        "/dev/grid/node-x(?<x>\\d+)-y(?<y>\\d+)\\s+(?<size>\\d+)T\\s+(?<used>\\d+)T.+"
            .toRegex()
            .matchEntire(line)?.destructured
            ?.let { (x, y, size, used) -> Node(Position2d(x.toInt(), y.toInt()), size.toInt(), used.toInt()) }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}