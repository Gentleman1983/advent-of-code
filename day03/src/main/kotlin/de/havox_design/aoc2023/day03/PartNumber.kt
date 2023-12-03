package de.havox_design.aoc2023.day03

data class PartNumber(val points: List<Point>) {
    fun getValue(): Long {
        var sum = 0L

        for (i: Int in points.indices) {
            sum *= 10
            sum += points[i].value.digitToInt()
        }

        return sum
    }
}
