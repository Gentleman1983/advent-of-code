package de.havox_design.aoc2019.day13

typealias Intcode = MutableMap<Int, Long>

fun Intcode.read(pointer: Long) = read(pointer.toInt())
fun Intcode.read(pointer: Int): Long {
    if (pointer < 0)
        throw IllegalArgumentException("Pointer can not be negative $pointer")
    return this.getOrDefault(pointer, 0L)
}

fun Intcode.write(pointer: Long, value: Long) = write(pointer.toInt(), value)
fun Intcode.write(pointer: Int, value: Long) {
    if (pointer < 0)
        throw IllegalArgumentException("Pointer can not be negative $pointer")
    this[pointer] = value
}