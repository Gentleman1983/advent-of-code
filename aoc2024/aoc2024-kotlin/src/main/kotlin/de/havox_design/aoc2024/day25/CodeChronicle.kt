package de.havox_design.aoc2024.day25

import de.havox_design.aoc.utils.kotlin.model.collections.Quintet

class CodeChronicle(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val (locks: List<Quintet<Int, Int, Int, Int, Int>>, keys:List<Quintet<Int, Int, Int, Int, Int>>) = data

        return locks
            .sumOf { lock ->
                keys
                    .count { key ->
                        lock.first + key.first <= 7 &&
                                lock.second + key.second <= 7 &&
                                lock.third + key.third <= 7 &&
                                lock.fourth + key.fourth <= 7 &&
                                lock.fifth + key.fifth <= 7
                    }
            }
    }


    fun processPart2(): Any =
        "Merry X-MAS!!!"

    @SuppressWarnings("kotlin:S3776")
    private fun parseInput(input: List<String>):
            Pair<MutableList<Quintet<Int, Int, Int, Int, Int>>, MutableList<Quintet<Int, Int, Int, Int, Int>>> {
        var current = Quintet(0, 0, 0, 0, 0)
        val keys = mutableListOf<Quintet<Int, Int, Int, Int, Int>>()
        val lock = mutableListOf<Quintet<Int, Int, Int, Int, Int>>()
        var isLock = false
        var row = 0

        input
            .forEach { currentRow ->
                if (currentRow.isBlank()) {
                    if (isLock) {
                        lock
                            .add(current)
                    } else {
                        keys
                            .add(current)
                    }

                    current = Quintet(0, 0, 0, 0, 0)
                    isLock = false
                    row = 0

                    return@forEach
                }

                if (row == 0) {
                    isLock = currentRow == ICON_LOCK
                }

                currentRow
                    .forEachIndexed { index, c ->
                        if (c == ICON_FILLED) {
                            when (index) {
                                0 -> current = current.copy(first = current.first + 1)
                                1 -> current = current.copy(second = current.second + 1)
                                2 -> current = current.copy(third = current.third + 1)
                                3 -> current = current.copy(fourth = current.fourth + 1)
                                4 -> current = current.copy(fifth = current.fifth + 1)
                            }
                        }
                    }

                row++
            }

        if (isLock) {
            lock
                .add(current)
        } else {
            keys
                .add(current)
        }

        return lock to keys
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ICON_FILLED = '#'
        private const val ICON_LOCK = "#####"
    }
}
