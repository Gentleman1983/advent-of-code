package de.havox_design.aoc2024.day20

class PathResult<T>(
    val start: Set<T>,
    val end: T?,
    // Maps vertices to costs and previous vertices
    private val allVertices: Map<T, SeenVertex<T>>,
    // Maps vertex to all possible previous vertices that still results in a shortest path
    val possiblePaths: Map<T, Set<T>> = emptyMap()
) {
    val cost: Int
        get() = end?.let { getCost(it) } ?: -1

    fun getCost(vertex: T): Int =
        allVertices[vertex]?.cost ?: throw IllegalStateException("Result for $vertex not available")

    fun getVertices(): List<T> = end?.let { getVertices(it, emptyList()) } ?: throw IllegalStateException("No path found")

    fun getVertices(end: T): List<T> = getVertices(end, emptyList())

    fun getVertexInPath(end: T, startCondition: (T) -> Boolean) =
        getPathItem(end, startCondition) ?: throw IllegalStateException("No path found")

    fun seen(): Set<T> = allVertices.keys

    private tailrec fun getVertices(endVertex: T, pathEnd: List<T>): List<T> {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            listOf(endVertex) + pathEnd
        } else {
            getVertices(previous, listOf(endVertex) + pathEnd)
        }
    }

    tailrec fun getStart(endVertex: T): T {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            endVertex
        } else {
            getStart(previous)
        }
    }

    private tailrec fun getPathItem(endVertex: T, startCondition: (T) -> Boolean = { it == null }): T? {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            null
        } else if (startCondition(previous)) {
            previous
        } else {
            getPathItem(previous, startCondition)
        }
    }
}
