package de.havox_design.aoc2018.day22

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import java.util.PriorityQueue

class ModeMaze(private var filename: String) {
    fun processTask1(): Any {
        val data = getData()
        val depth = data.first
        val target = data.second

        return getRiskLevel(depth, target)
    }

    fun processTask2(): Any {
        val data = getData()
        val depth = data.first
        val target = data.second

        return getShortestTimeTo(depth, target)
    }

    private fun getRiskLevel(depth: Int, target: Position2d<Int>) =
        HardCave(depth, target)
            .apply {
                (0..target.y)
                    .forEach { y ->
                        (0..target.x)
                            .forEach { x ->
                                at(Position2d(x, y))
                            }
                    }
            }
            .getTotalRiskRiskLevel()

    private fun getShortestTimeTo(depth: Int, target: Position2d<Int>): Int {
        val cave = HardCave(depth, target)
        val start = cave
            .at(Position2d(0, 0))
            .let { CaveStep(it, it.toolsRequired().first()) }
        val seenShortest = mutableMapOf(start.asKey to start.cost)
        val stepsRemaining = PriorityQueue<CaveStep>().apply { add(start) }

        while (stepsRemaining.isNotEmpty()) {
            val step = stepsRemaining.poll()

            if (step.at.point == target && step.using == Tool.TORCH) {
                return step.cost
            }

            step
                .generateSteps(cave)
                .forEach {
                    val existing = seenShortest[it.asKey]

                    if (existing == null || it.cost < existing) {
                        seenShortest[it.asKey] = it.cost
                        stepsRemaining += it
                    }
                }
        }

        return -1
    }

    private fun getData(): Pair<Int, Position2d<Int>> {
        val input = getResourceAsText(filename)

        val depth = input[0].split(':')[1].trim().toInt()
        val target = Position2d(
            input[1].split(':')[1].trim().split(',')[0].trim().toInt(),
            input[1].split(':')[1].trim().split(',')[1].trim().toInt()
        )

        return Pair(depth, target)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
