package de.havox_design.aoc2020.day17

sealed class TrieNode {
    abstract fun count(): Int

    abstract fun countWithNeighbours(location: IntArray): Int

    class InnerNode(override val lowerBounds: IntArray, override val upperBounds: IntArray, val dimension: Int) :
        TrieNode(), IntTrie {
        private val lowerBound get() = lowerBounds[dimension]
        private val upperBound get() = upperBounds[dimension]
        private val nodes: Array<TrieNode> = Array(upperBound - lowerBound + 1) { EmptyNode }

        override fun add(location: IntArray) {
            val nodeIndex = location[dimension] - lowerBound

            if (dimension == location.lastIndex) {
                nodes[nodeIndex] = LeafNode
            } else {
                val trieNode = nodes[nodeIndex]

                if (trieNode is InnerNode) {
                    trieNode.add(location)
                } else {
                    val newNode = InnerNode(lowerBounds, upperBounds, dimension + 1)

                    newNode.add(location)
                    nodes[nodeIndex] = newNode
                }
            }
        }

        override fun contains(location: IntArray): Boolean {
            val nodeIndex = location[dimension] - lowerBound

            if (nodeIndex !in nodes.indices) {
                return false
            }

            return when (val nextNode = nodes[nodeIndex]) {
                is EmptyNode -> false
                is LeafNode -> true
                is InnerNode -> nextNode.contains(location)
            }
        }

        override fun count(): Int =
            nodes
                .sumOf { it.count() }

        override fun countWithNeighbours(location: IntArray): Int {
            val nodeIndex = location[dimension] - lowerBound

            return countWithNeighbours(nodeIndex, location) +
                    countWithNeighbours(nodeIndex - 1, location) +
                    countWithNeighbours(nodeIndex + 1, location)
        }

        override fun subtree(index: Int): IntTrie? {
            val nodeIndex = index - lowerBound

            if (nodeIndex !in nodes.indices) {
                return null
            }

            return when (val nextNode = nodes[nodeIndex]) {
                is InnerNode -> nextNode
                else -> null
            }
        }

        private fun countWithNeighbours(nodeIndex: Int, location: IntArray) =
            if (nodeIndex in nodes.indices) {
                nodes[nodeIndex].countWithNeighbours(location)
            } else {
                0
            }
    }
}
