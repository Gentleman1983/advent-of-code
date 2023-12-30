package de.havox_design.aoc2023.day25

import org.jgrapht.Graph
import org.jgrapht.alg.flow.GusfieldGomoryHuCutTree
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

class Day25(private var filename: String) {
    private val CONNECTING_DELIMITER = ": "
    private val ELEMENT_DELIMITER = " "

    fun solvePart1(): Long =
        splitGroups(getResourceAsText(filename))
            .toLong()

    fun solvePart2(): String =
        "Merry X-MAS!!!"

    private fun splitGroups(input: List<String>): Int {
        val graph: Graph<String, DefaultEdge> = SimpleGraph(DefaultEdge::class.java)

        for (row in input) {
            val split = row
                .split(CONNECTING_DELIMITER)
            val source = split[0]
            val destinationSplit = split[1]
                .split(ELEMENT_DELIMITER)
                .toMutableList()

            graph.addVertex(source)
            for (destination in destinationSplit) {
                graph.addVertex(destination)
                graph.addEdge(source, destination)
            }
        }

        val gusfieldGomoryHuCutTree = GusfieldGomoryHuCutTree(graph)

        gusfieldGomoryHuCutTree.calculateMinCut()

        val sourcePartition = gusfieldGomoryHuCutTree.sourcePartition
        val remainingPartitionSize = graph.vertexSet().size - sourcePartition.size

        return remainingPartitionSize * sourcePartition.size
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}