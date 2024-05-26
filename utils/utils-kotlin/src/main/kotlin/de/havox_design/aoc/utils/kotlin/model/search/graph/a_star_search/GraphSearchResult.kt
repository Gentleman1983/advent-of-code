package de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search

class GraphSearchResult<K>(val start: K, val end: K, private val result: Map<K, SeenVertex<K>>) {
    fun getScore(vertex: K = end) =
        result[vertex]
            ?.score
            ?: throw IllegalStateException("Result for $vertex not available")

    tailrec fun getPath(endVertex: K = end, pathEnd: List<K> = emptyList()): List<K> {
        val previous = result[endVertex]?.prev

        return if (previous == null) {
            listOf(endVertex) + pathEnd
        } else {
            getPath(previous, listOf(endVertex) + pathEnd)
        }
    }
}
