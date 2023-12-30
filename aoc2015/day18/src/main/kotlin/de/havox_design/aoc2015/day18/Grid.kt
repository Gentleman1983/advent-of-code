package de.havox_design.aoc2015.day18

interface Grid {
    fun turnOn(x: Int, y: Int)
    fun turnOff(x: Int, y: Int)
    fun toggle(x: Int, y: Int)
    fun lights(): Int
}