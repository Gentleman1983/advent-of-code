package de.havox_design.aoc2018.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc2018.day11.e
import de.havox_design.aoc2018.day11.n
import de.havox_design.aoc2018.day11.s
import de.havox_design.aoc2018.day11.w

data class CaveStep(val at: Region, val using: Tool, val cost: Int = 0) :
    Comparable<CaveStep> {

    val asKey: Pair<Region, Tool> by lazy { at to using }

    override fun compareTo(other: CaveStep) = cost.compareTo(other.cost)

    fun generateSteps(cave: HardCave) = stepSameTools(cave) + stepOtherTools()

    private fun stepSameTools(cave: HardCave) = getCardinal(at.point)
        .asSequence()
        .filter { it.isPositive() }
        .map { Region(it, cave) }
        .filter { using in it.toolsRequired() }
        .map { CaveStep(it, using, cost + 1) }

    private fun stepOtherTools() = at.toolsRequired().minus(using)
        .asSequence()
        .map { CaveStep(at, it, cost + 7) }

    private fun getCardinal(p: Position2d<Int>): Collection<Position2d<Int>> =
        listOf(p.e(), p.n(), p.s(), p.w())
}

private fun Position2d<Int>.isPositive() =
    x >= 0 && y >= 0
