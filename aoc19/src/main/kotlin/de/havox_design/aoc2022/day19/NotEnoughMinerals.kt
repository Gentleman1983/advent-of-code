package de.havox_design.aoc2022.day19

class NotEnoughMinerals(private var filename: String) {
    val blueprints = readFile()
    val qualityLevels = emptyMap<Blueprint, Int>().toMutableMap()

    fun processPart1(): Int {
        val data = blueprints.map { blueprint -> BlueprintSimulation(blueprint).simulateBlueprint() }.toList()
        return data.sum()
    }


    fun processPart2(): Int =
        0

    private fun readFile(): List<Blueprint> {
        val fileData = getResourceAsText(filename)

        return fileData.map { row ->
            Blueprint(
                id = row
                    .substringAfter("Blueprint ")
                    .substringBefore(":")
                    .toInt(),
                costOreRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each ore robot costs ")
                        .substringBefore(" ore. Each clay robot costs ")
                        .toInt()
                ),
                costClayRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each clay robot costs ")
                        .substringBefore(" ore. Each obsidian robot costs ")
                        .toInt()
                ),
                costObsidianRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each obsidian robot costs ")
                        .substringBefore(" ore and ") //NOSONAR reoccuring String is wanted for better readability
                        .toInt(),
                    clay = row
                        .substringAfter(" ore and ") //NOSONAR see before
                        .substringBefore(" clay. Each geode robot costs ")
                        .toInt()
                ),
                costGeodeRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each geode robot costs ")
                        .substringBeforeLast(" ore and ") //NOSONAR see before
                        .toInt(),
                    obsidian = row
                        .substringAfterLast(" ore and ") //NOSONAR see before
                        .substringBefore(" obsidian.")
                        .toInt()
                )
            )
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
