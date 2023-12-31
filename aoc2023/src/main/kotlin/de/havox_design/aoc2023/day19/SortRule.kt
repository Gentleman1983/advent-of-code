package de.havox_design.aoc2023.day19

data class SortRule(
    val name: String,
    val decisions: List<Condition>,
    val elseRule: String
)
