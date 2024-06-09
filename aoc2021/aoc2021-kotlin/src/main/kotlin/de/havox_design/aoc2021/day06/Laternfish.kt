package de.havox_design.aoc2021.day06

class Laternfish(private var filename: String) {
    private val FISH_DELIMITER = ","
    private val FISH_TIMER_BIRTH = -1
    private val FISH_TIMER_NEW = 8
    private val FISH_TIMER_RESET = 6
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        var day = 0
        val fishes = parseData().toMutableList()

        while (day < 80) {
            processDay(fishes)
            day++
        }

        return fishes.size
    }

    fun processPart2(): Any {
        val initialFish = parseData()
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, value) -> value.toLong() }

        val fish = (1..256)
            .fold(initialFish) { fish, _ ->
                val newFish = fish
                    .mapKeys { (key, _) -> key - 1 }
                    .toMutableMap()

                newFish[FISH_TIMER_NEW] = newFish.getOrDefault(FISH_TIMER_BIRTH, 0)
                newFish[FISH_TIMER_RESET] =
                    newFish.getOrDefault(FISH_TIMER_RESET, 0) + newFish.getOrDefault(FISH_TIMER_BIRTH, 0)
                newFish.remove(-1)

                newFish
            }

        return fish
            .values
            .sumOf { it }
    }

    private fun processDay(fishes: MutableList<Int>) {
        val originalLength = fishes.size

        for (i in 0..<originalLength) {
            fishes[i]--

            if (fishes[i] < 0) {
                fishes[i] = FISH_TIMER_RESET
                fishes.addLast(FISH_TIMER_NEW)
            }
        }
    }

    private fun parseData() =
        data
            .split(FISH_DELIMITER)
            .map { element ->
                element
                    .trim()
                    .toInt()
            }
            .toList()

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
