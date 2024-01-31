package de.havox_design.aoc2015.day18

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

interface Grid {
    fun turnOn(position: Position2d<Int>)
    fun turnOff(position: Position2d<Int>)
    fun toggle(position: Position2d<Int>)
    fun lights(): Int
}