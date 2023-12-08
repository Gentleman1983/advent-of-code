package de.havox_design.aoc2016.day22

data class Node(val x: Int, val y: Int, val size: Int, val used: Int) {
    val available = size - used
}
