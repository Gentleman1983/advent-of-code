package de.havox_design.aoc2020.day17

interface IntTrie {

    val lowerBounds: IntArray
    val upperBounds: IntArray

    fun count(): Int

    fun countWithNeighbours(location: IntArray): Int

    fun add(location: IntArray)

    fun add(location: HyperspacePoint) {
        add(location.parts)
    }

    fun addAll(locations: Collection<HyperspacePoint>) {
        locations
            .forEach(this::add)
    }

    fun contains(location: IntArray): Boolean

    fun contains(location: HyperspacePoint): Boolean =
        contains(location.parts)

    fun subtree(index: Int): IntTrie?

    companion object {
        fun create(lowerBounds: IntArray, upperBounds: IntArray): IntTrie {
            require(lowerBounds.size == upperBounds.size) { "Lower and upper bounds arrays must have the same size" }

            return TrieNode
                .InnerNode(lowerBounds, upperBounds, 0)
        }
    }
}
