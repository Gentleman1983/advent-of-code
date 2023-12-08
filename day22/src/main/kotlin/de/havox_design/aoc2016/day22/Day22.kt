package de.havox_design.aoc2016.day22

class Day22(private var filename: String) {
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

    fun solvePart2(): Long =
        0L

    private fun parse(line: String): Node? =
        "/dev/grid/node-x(?<x>\\d+)-y(?<y>\\d+)\\s+(?<size>\\d+)T\\s+(?<used>\\d+)T.+"
            .toRegex()
            .matchEntire(line)?.destructured
            ?.let { (x, y, size, used) -> Node(x.toInt(), y.toInt(), size.toInt(), used.toInt()) }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}