package de.havox_design.aoc2023.day08

data class Node(val name: String, var left: Node?, var right: Node?) {
    fun setNode(direction: Direction, node: Node) {
        when(direction) {
            Direction.LEFT -> left = node
            Direction.RIGHT -> right = node
        }
    }

    fun setNodes(left: Node, right: Node) {
        setNode(Direction.LEFT, left)
        setNode(Direction.RIGHT, right)
    }

    fun getNode(direction: Direction): Node =
        when(direction) {
            Direction.LEFT -> left!!
            Direction.RIGHT -> right!!
        }
}
