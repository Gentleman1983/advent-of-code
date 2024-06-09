package de.havox_design.aoc2023.day01

class Trebuchet(private var filename: String) {
    private val words = listOf(
        Digit("zero", "0"),
        Digit("one", "1"),
        Digit("two", "2"),
        Digit("three", "3"),
        Digit("four", "4"),
        Digit("five", "5"),
        Digit("six", "6"),
        Digit("seven", "7"),
        Digit("eight", "8"),
        Digit("nine", "9"),
        Digit("0", "0"),
        Digit("1", "1"),
        Digit("2", "2"),
        Digit("3", "3"),
        Digit("4", "4"),
        Digit("5", "5"),
        Digit("6", "6"),
        Digit("7", "7"),
        Digit("8", "8"),
        Digit("9", "9")
    )

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
        getResourceAsText(filename)
            .sumOf { row -> replaceDigitWordsSmart(row) }

    private fun replaceDigitWordsSmart(row: String): Long {
        val firstDigit: String
        val lastDigit: String
        val wordPositions = words
            .flatMap { digit -> getDigitIndices(row, digit) }
            .filter { pos -> pos.position != -1 }
            .sortedBy { pos -> pos.position }

        if (wordPositions.isEmpty()) {
            return 0
        }

        firstDigit = wordPositions
            .first()
            .digit
            .value
        lastDigit = wordPositions
            .last()
            .digit
            .value

        return (firstDigit + lastDigit)
            .toLong()
    }

    private fun getDigitIndices(input: String, digit: Digit): Set<DigitPosition> {
        val indices = ArrayList<Int>()
        var index: Int = input
            .indexOf(digit.word)

        while (index >= 0) {
            indices.add(index)
            index = input
                .indexOf(digit.word, index + 1)
        }

        return indices
            .map { DigitPosition(digit, it) }
            .toSet()
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
