package de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search

import java.util.*

inline fun <K> findShortestPath(
    start: K,
    end: K,
    crossinline neighbours: (K) -> Iterable<K>,
    crossinline cost: (K, K) -> Int,
    crossinline heuristic: (K) -> Int = { 0 }
): GraphSearchResult<K> {
    val toVisit = PriorityQueue(listOf(ScoredVertex(start, 0, heuristic(start))))
    val seenPoints: MutableMap<K, SeenVertex<K>> = mutableMapOf(start to SeenVertex(0, null))

    while (!seenPoints.containsKey(end)) {
        val (currentVertex, currentScore) = toVisit.remove()
        val nextPoints = neighbours(currentVertex)
            .filter { it !in seenPoints }
            .map { next -> ScoredVertex(next, currentScore + cost(currentVertex, next), heuristic(next)) }

        toVisit.addAll(nextPoints)
        seenPoints.putAll(nextPoints.associate { it.vertex to SeenVertex(it.score, currentVertex) })
    }

    return GraphSearchResult(start, end, seenPoints)
}
