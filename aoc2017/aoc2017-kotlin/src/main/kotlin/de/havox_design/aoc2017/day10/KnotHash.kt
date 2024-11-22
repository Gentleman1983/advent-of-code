package de.havox_design.aoc2017.day10

class KnotHash(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(size: Int = 256): Any =
        data
            .split(",")
            .map { it
                .trim()
                .toInt()
            }
            .encode(size)
            .take(2)
            .reduce { a, b -> a * b }

    fun processPart2(): Any =
        knotHash(data)

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}

fun knotHash(value: String) =
    (value.map { it.code } + listOf(17, 31, 73, 47, 23))
        .encode(rounds = 64)
        .chunked(16) { chunk ->
            chunk
                .reduce { a, b ->
                    a xor b
                }
        }
        .joinToString("") { "%02x".format(it) }

private fun List<Int>.encode(size: Int = 256, rounds: Int = 1): List<Int> {
    val items = (0 until size)
        .toMutableList()
    var index = 0
    var skip = 0

    repeat(rounds) {
        forEach { len ->
            items
                .reverseSubList(index, len)
            index = (index + len + skip++) % items.size
        }
    }

    return items
}

private fun MutableList<Int>.reverseSubList(start: Int, len: Int) =
    repeat(len / 2) { pos ->
        val aIndex = (start + pos) % size
        val bIndex = (start + len - 1 - pos) % size

        this[aIndex] = this[bIndex].also { this[bIndex] = this[aIndex] }
    }
