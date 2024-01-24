package de.havox_design.aoc.utils.kotlin

interface AocMainClassHelper {
    fun day(
        day: String,
        solutionPart1: () -> Any,
        solutionPart2: () -> Any,
        daysSelected: Boolean,
        args: Array<String>
    ) {
        if (!daysSelected || args.contains(day.lowercase())) {
            solveDay(day, solutionPart1.invoke(), solutionPart2.invoke())
        }
    }

    fun solveDay(day: String, solutionPart1: Any, solutionPart2: Any) {
        println("${day}: Solution part 1: $solutionPart1")
        println("${day}: Solution part 2: $solutionPart2")
    }

    fun getFileName(day: Int): String {
        var dayString = day.toString()
        if (day < 10) {
            dayString = "0$dayString"
        }

        return "de/havox_design/aoc${getYear()}/day${dayString}/day${dayString}.txt"
    }

    fun getYear(): Int

    fun getDayString(day: Int): String {
        var dayString = day.toString()
        if (day < 10) {
            dayString = "0$dayString"
        }

        return "Day${dayString}"
    }

    fun processYear(args: Array<String>)
}
