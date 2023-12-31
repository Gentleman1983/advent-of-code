package de.havox_design.aoc2023.day03

class GearRatios(private var filename: String) {
    private val schematic = EngineSchematic(getResourceAsText(filename))

    fun solvePart1(): Long =
            schematic
                    .sumOfPartNumbers()

    fun solvePart2(): Long =
            schematic
                    .sumOfGearRatios()

    private fun getResourceAsText(path: String): List<String> =
            this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}