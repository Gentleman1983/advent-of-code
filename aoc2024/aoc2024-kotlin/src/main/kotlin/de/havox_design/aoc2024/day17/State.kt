package de.havox_design.aoc2024.day17

data class State(
    var a: Register,
    var b: Register,
    var c: Register,
    val program: Program,
    var output: Output = mutableListOf()
)

typealias Register = Long
typealias Program = List<Int>
typealias Output = List<Int>
