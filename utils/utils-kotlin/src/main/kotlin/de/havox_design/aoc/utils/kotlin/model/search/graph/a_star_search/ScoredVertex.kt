package de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search

data class ScoredVertex<K>(val vertex: K, val score: Int, val heuristic: Int) : Comparable<ScoredVertex<K>> {
    override fun compareTo(other: ScoredVertex<K>): Int =
        (score + heuristic).compareTo(other.score + heuristic)
}
