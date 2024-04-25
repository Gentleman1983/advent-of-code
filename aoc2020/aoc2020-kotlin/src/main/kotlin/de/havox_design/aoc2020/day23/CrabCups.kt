package de.havox_design.aoc2020.day23

class CrabCups(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(iterations: Int = 100): Any {
        val labelsList = data
            .map { it.toString().toInt() }
        val cups = IntCircularList(10, labelsList)
        var currentCup = labelsList[0]

        repeat(iterations) {
            currentCup = runIteration(cups, 9, currentCup)
        }

        val stringBuilder = StringBuilder(data.length)
        var current = cups.getNext(1)

        while (current != 1) {
            stringBuilder.append(current)
            current = cups.getNext(current)
        }

        return stringBuilder
            .toString()
    }

    fun processPart2(): Any =
        0L

    private fun runIteration(cups: IntCircularList, maxCup: Int, currentCup: Int): Int {
        val pickup1 = cups.remove(cups.getNext(currentCup))
        val pickup2 = cups.remove(cups.getNext(currentCup))
        val pickup3 = cups.remove(cups.getNext(currentCup))
        val destinationCup = findDestination(currentCup, maxCup, pickup1, pickup2, pickup3)

        cups.addAfter(destinationCup, listOf(pickup1, pickup2, pickup3))

        return cups.getNext(currentCup)
    }

    private fun findDestination(currentCup: Int, maxCup: Int, pickup1: Int, pickup2: Int, pickup3: Int): Int {
        var destinationCup = currentCup

        do {
            destinationCup -= 1

            if (destinationCup < 1) {
                destinationCup = maxCup
            }
        } while (destinationCup == pickup1 || destinationCup == pickup2 || destinationCup == pickup3)

        return destinationCup
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
