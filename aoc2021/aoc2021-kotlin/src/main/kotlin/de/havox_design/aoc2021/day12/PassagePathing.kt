package de.havox_design.aoc2021.day12

class PassagePathing(private var filename: String) {
    private val DELIMITER_CONNECTION = "-"
    private val NODE_NAME_END = "end"
    private val NODE_NAME_START = "start"

    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val edges = data
            .map { it.split(DELIMITER_CONNECTION) }
            .flatMap {
                listOf(
                    it.first() to it.last(),
                    it.last() to it.first()
                )
            }
            .groupBy(
                keySelector = { it.first },
                valueTransform = { it.second },
            )
            .mapValues { it.value.sorted() }

        val visited = mutableSetOf<List<String>>()
        val finish = mutableSetOf<List<String>>()
        val queue = ArrayDeque(listOf(listOf(NODE_NAME_START)))

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()

            if (curr !in visited) {
                visited += curr

                val tail = curr.last()

                if (tail == NODE_NAME_END) {
                    finish += curr
                } else {
                    queue += edges[tail]
                        .orEmpty()
                        .filter { it.uppercase() == it || it !in curr }
                        .map { curr + it }
                        .filter { it !in visited }
                }
            }
        }

        return finish.size
    }

    fun processPart2(): Any {
        val edges = data
            .map { it.split(DELIMITER_CONNECTION) }
            .flatMap {
                listOf(
                    it.first() to it.last(),
                    it.last() to it.first()
                )
            }
            .groupBy(
                keySelector = { it.first },
                valueTransform = { it.second },
            )
            .mapValues { it.value.sorted() }

        val visited = mutableSetOf<List<String>>()
        val finish = mutableSetOf<List<String>>()
        val queue = ArrayDeque(listOf(listOf(NODE_NAME_START) to false))

        while (queue.isNotEmpty()) {
            val (curr, twice) = queue.removeFirst()

            if (curr !in visited) {
                visited += curr

                val tail = curr.last()
                val nexts = edges[tail].orEmpty()

                if (NODE_NAME_END in nexts) {
                    finish += curr + NODE_NAME_END
                }

                queue += nexts
                    .asSequence()
                    .filter { it !in setOf(NODE_NAME_START, NODE_NAME_END) }
                    .filter { it.uppercase() == it || it !in curr }
                    .map { curr + it }
                    .filter { it !in visited }
                    .map { it to twice }

                if (!twice) {
                    queue += nexts
                        .asSequence()
                        .filter { it !in setOf(NODE_NAME_START, NODE_NAME_END) }
                        .filter { it.lowercase() == it && it in curr }
                        .map { curr + it }
                        .filter { it !in visited }
                        .map { it to true }
                }
            }
        }

        return finish.size
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
