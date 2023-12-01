package de.havox_design.aoc2023.day01

class Day01(private var filename: String) {
    fun solvePart1(): Long {
        val payloads = getResourceAsText(filename)
        var calibrationValue = 0L

        for(payload:String in payloads) {
            val numbers = payload
                .chars()
                .mapToObj { c -> c.toChar() }
                .filter(Char::isDigit)
                .mapToInt { c -> c.digitToInt() }
                .toArray()
                .toList()

            val firstNumber = numbers.first()
            val lastNumber = numbers.last()

            val currentCalibrationValue = firstNumber * 10 + lastNumber
            calibrationValue += currentCalibrationValue
        }

        return calibrationValue
    }

    fun solvePart2(): Long =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
