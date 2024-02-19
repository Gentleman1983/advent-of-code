package de.havox_design.aoc2018.day18

enum class Content(val output: Char) {
    OPEN('.'),
    TREES('|'),
    LUMBERYARD('#');

    companion object {
        fun parse(value: Char) =
            entries
                .find { it.output == value }
            ?: throw IllegalArgumentException("Unknown content.")
    }
}
