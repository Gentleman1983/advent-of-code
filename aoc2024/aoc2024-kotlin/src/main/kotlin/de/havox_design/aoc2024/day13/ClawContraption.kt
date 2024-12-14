package de.havox_design.aoc2024.day13

import kotlin.math.roundToLong

class ClawContraption(private var filename: String) {
    private val data = parseData(getResourceAsText(filename))

    fun processPart1(): Any {
        var minTokenCount = 0L


        data
            .forEach { machineData ->
                solveLinearSystem(
                    a1 = machineData
                        .buttonA
                        .first,
                    b1 = machineData
                        .buttonB
                        .first,
                    c1 = machineData
                        .prize
                        .first,
                    a2 = machineData
                        .buttonA
                        .second,
                    b2 = machineData
                        .buttonB
                        .second,
                    c2 = machineData
                        .prize
                        .second,
                )
                    ?.also { solution ->
                        if (solution.first in (0.0..100.0) &&
                            solution.first % 1 == 0.0 &&
                            solution.second in (0.0..100.0) &&
                            solution.second % 1 == 0.0
                        ) {
                            minTokenCount += (PRIZE_BUTTON_A * solution.first + PRIZE_BUTTON_B * solution.second)
                                .roundToLong()
                        }
                    }
            }

        return minTokenCount
    }

    fun processPart2(): Any {
        var minTokenCount = 0L

        data.forEach { machineData ->
            solveLinearSystem(
                a1 = machineData
                    .buttonA
                    .first,
                b1 = machineData
                    .buttonB
                    .first,
                c1 = 10000000000000 + machineData
                    .prize
                    .first,
                a2 = machineData
                    .buttonA
                    .second,
                b2 = machineData
                    .buttonB
                    .second,
                c2 = 10000000000000 + machineData
                    .prize
                    .second,
            )
                ?.also { solution ->
                    if (solution.first > 0.0 &&
                        solution.first % 1 == 0.0 &&
                        solution.second > 0.0 &&
                        solution.second % 1 == 0.0
                    ) {
                        minTokenCount += (PRIZE_BUTTON_A * solution.first + PRIZE_BUTTON_B * solution.second)
                            .roundToLong()
                    }
                }
        }

        return minTokenCount
    }

    private fun solveLinearSystem(a1: Long, b1: Long, c1: Long, a2: Long, b2: Long, c2: Long): Pair<Double, Double>? {
        return when (val determinant = a1.toDouble() * b2 - a2.toDouble() * b1) {
            0.0 -> null

            else -> {
                val x = (b2 * c1 - b1 * c2) / determinant
                val y = (a1 * c2 - a2 * c1) / determinant

                x to y
            }
        }
    }

    private fun parseData(input: List<String>) =
        input
            .filter(String::isNotEmpty)
            .chunked(3)
            .map { rawMachineSpecs ->
                val (buttonA, buttonB) = rawMachineSpecs
                    .subList(0, 2)
                    .map {
                        it
                            .substringAfter("X+")
                            .substringBefore(",")
                            .toLong() to
                                it
                                    .substringAfter("Y+")
                                    .toLong()
                    }

                val prize = rawMachineSpecs[2]
                    .let {
                        it
                            .substringAfter("X=")
                            .substringBefore(",")
                            .toLong() to
                                it
                                    .substringAfter("Y=")
                                    .toLong()
                    }

                MachineSpecifications(
                    buttonA = buttonA,
                    buttonB = buttonB,
                    prize = prize
                )
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val PRIZE_BUTTON_A = 3
        private const val PRIZE_BUTTON_B = 1
    }
}
