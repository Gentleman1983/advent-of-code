package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.plus

class PyroclasticFlow(private var filename: String) {
    val jetPattern = readFile()
    private var numberOfJetsPerformed: Long = 0
    private val rockSequence = arrayOf(Rock.HORIZONTAL_LINE, Rock.PLUS, Rock.ARROW, Rock.VERTICAL_LINE, Rock.BOX)
    val chamber = Chamber()

    fun processPart1(numberOfStones: Long = 2022): Long {
        var counter = 0

        while (counter < numberOfStones) {
            val nextRock = rockSequence[counter % rockSequence.size]

            performRock(nextRock)
            counter++
        }

        return chamber.getMaxHeight()
    }

    fun processPart2(): Long =
        processPart1(1000000000000)

    private fun performRock(rock: Rock) {
        var currentPosition = chamber.getStartPositionForRock()
        var currentJet = jetPattern[(numberOfJetsPerformed % jetPattern.size).toInt()]

        while (!chamber.addRockToObstaclesIfItCollides(rock, currentPosition, currentJet)) {
            numberOfJetsPerformed++
            currentPosition += Jet.getPositionForJet(chamber.getRealDirection(rock, currentPosition, currentJet))
            currentJet = jetPattern[(numberOfJetsPerformed % jetPattern.size).toInt()]

            // Drop a row
            if (chamber.addRockToObstaclesIfItCollides(rock, currentPosition, Jet.DOWN)) {
                numberOfJetsPerformed--
                break
            }
            currentPosition += Jet.getPositionForJet(Jet.DOWN)
        }
        numberOfJetsPerformed++
    }

    private fun readFile(): List<Jet> {
        val fileData = getResourceAsText(filename)
        val jetSequence = emptyList<Jet>().toMutableList()

        for (index in fileData[0].indices) {
            val code: String = fileData[0][index].toString()

            jetSequence.add(Jet.getJetForCode(code))
        }

        return jetSequence
    }


    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
