package de.havox_design.aoc2020.day17

object EmptyNode : TrieNode() {
    override fun count(): Int =
        0
    override fun countWithNeighbours(location: IntArray): Int =
        0
}
