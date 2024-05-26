package de.havox_design.aoc2021.day23

data class World(
    val spots: Map<Node, Char>,
    val stacks: Map<Node, List<Char>>,
)
