package de.havox_design.aoc2020.day17

object LeafNode : TrieNode() {
    override fun count(): Int =
        1

    override fun countWithNeighbours(location: IntArray) =
        1
}
