package de.havox_design.aoc2021.day23

data class Link(
    val to: Node,
    val blocking: List<Node>,
    val costX: Int,
)
