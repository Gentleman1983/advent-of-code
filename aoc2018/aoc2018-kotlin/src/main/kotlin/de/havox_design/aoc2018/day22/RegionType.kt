package de.havox_design.aoc2018.day22

enum class RegionType {
    ROCKY,
    WET,
    NARROW;

    val riskLevel = ordinal
}