package de.havox_design.aoc2023.day08

import de.havox_design.aoc.utils.kotlin.model.directions.LeftRightDirection

data class Node(val name: String, var left: Node?, var right: Node?) : Comparable<Node> {
    fun setNode(direction: LeftRightDirection, node: Node) {
        when (direction) {
            LeftRightDirection.LEFT -> left = node
            LeftRightDirection.RIGHT -> right = node
        }
    }

    fun setNodes(left: Node, right: Node) {
        setNode(LeftRightDirection.LEFT, left)
        setNode(LeftRightDirection.RIGHT, right)
    }

    fun getNode(direction: LeftRightDirection): Node =
        when (direction) {
            LeftRightDirection.LEFT -> left!!
            LeftRightDirection.RIGHT -> right!!
        }

    override fun compareTo(other: Node): Int =
        name.compareTo(other.name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
