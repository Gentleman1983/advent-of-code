package de.havox_design.aoc2021.day22

class ReactorReboot(private var filename: String) {
    private val ICON_ON = "on"

    private val ID_STATUS = 1
    private val ID_X_MAX = 3
    private val ID_X_MIN = 2
    private val ID_Y_MAX = 5
    private val ID_Y_MIN = 4
    private val ID_Z_MAX = 7
    private val ID_Z_MIN = 6

    private val data = getResourceAsText(filename)
    private val pattern = "(on|off) x=([-0-9]+)..([-0-9]+),y=([-0-9]+)..([-0-9]+),z=([-0-9]+)..([-0-9]+)"
        .toRegex()

    fun processPart1(): Any {
        val steps = data
            .map { parseLine(it) }
            .map { it.clampTo(-50..50) }
            .filter { !it.cuboid.isEmpty() }

        return doReboot(steps)
    }

    fun processPart2(): Any =
        0L

    private fun doReboot(steps: List<RebootStep>): Long {
        val turnedOn: List<Cuboid> = steps
            .fold(emptyList()) { acc, instruction ->
                if (instruction.on) {
                    acc + instruction.cuboid
                } else {
                    acc.flatMap { it.subtract(instruction.cuboid) }
                }
            }

        return deduplicate(turnedOn).sumOf { it.volume }
    }

    private fun deduplicate(cuboids: Collection<Cuboid>): Collection<Cuboid> {
        val counted = mutableListOf<Cuboid>()

        for (next in cuboids) {
            val overlaps = counted.asSequence().filter { it.overlaps(next) }
            val new = overlaps.fold(sequenceOf(next)) { new, existing ->
                new.flatMap { it.subtract(existing) }
            }
            counted.addAll(new)
        }

        return counted
    }

    private fun parseLine(line: String): RebootStep {
        val matchResult = pattern.matchEntire(line)!!
        val on = matchResult.groupValues[ID_STATUS] == ICON_ON
        val x = matchResult.groupValues[ID_X_MIN].toInt()..matchResult.groupValues[ID_X_MAX].toInt()
        val y = matchResult.groupValues[ID_Y_MIN].toInt()..matchResult.groupValues[ID_Y_MAX].toInt()
        val z = matchResult.groupValues[ID_Z_MIN].toInt()..matchResult.groupValues[ID_Z_MAX].toInt()
        return RebootStep(on, Cuboid(x, y, z))
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
