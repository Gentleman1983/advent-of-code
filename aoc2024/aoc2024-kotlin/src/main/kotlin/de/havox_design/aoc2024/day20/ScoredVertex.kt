package de.havox_design.aoc2024.day20

data class ScoredVertex<T>(val vertex: T, val score: Int, val heuristic: Int) : Comparable<ScoredVertex<T>> {
    override fun compareTo(other: ScoredVertex<T>): Int =
        (score + heuristic)
            .compareTo(other.score + other.heuristic)
}
