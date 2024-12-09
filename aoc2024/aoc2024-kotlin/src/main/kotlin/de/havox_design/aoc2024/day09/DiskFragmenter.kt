package de.havox_design.aoc2024.day09

class DiskFragmenter(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val memory = data
            .toMutableList()

        while (memory.hasFreeSpace()) {
            val emptyBlock = memory
                .withIndex()
                .first { it.value.type == Type.FREE }
            val blockToMove = memory
                .withIndex()
                .last { it.value.type == Type.USED }

            when {
                emptyBlock.value.length == blockToMove.value.length -> {
                    memory[emptyBlock.index] = blockToMove.value
                    memory
                        .removeAt(blockToMove.index)
                }

                emptyBlock.value.length > blockToMove.value.length -> {
                    memory
                        .removeAt(blockToMove.index)
                    memory[emptyBlock.index] = blockToMove.value
                    memory
                        .add(
                            emptyBlock.index + 1,
                            Block(null, Type.FREE, emptyBlock.value.length - blockToMove.value.length)
                        )
                }

                else -> {
                    memory[blockToMove.index] =
                        Block(blockToMove.value.value, Type.USED, blockToMove.value.length - emptyBlock.value.length)
                    memory[emptyBlock.index] = Block(blockToMove.value.value, Type.USED, emptyBlock.value.length)
                }
            }

            if (memory.last().type == Type.FREE) {
                memory
                    .removeAt(memory.lastIndex)
            }
        }

        return memory
            .calculateCheckSum()
    }

    fun processPart2(): Any =
        0L

    private fun List<Block>.hasFreeSpace(): Boolean =
        any { it.type == Type.FREE }

    private fun List<Block>.calculateCheckSum() =
        fold(Pair(0, 0L)) { (index, overAllCheckSum), block ->
            val checkSum = (0 until block.length)
                .sumOf { i ->
                    (index + i) * (block.value ?: 0)
                        .toLong()
                }

            Pair(block.length + index, overAllCheckSum + checkSum)
        }
            .second

    private fun String.extractToList(pattern: Regex) =
        pattern
            .findAll(this)
            .map { it.value }
            .toList()

    private fun parseInput(input: List<String>): List<Block> =
        input[0]
            .extractToList("\\d".toRegex())
            .map { it.toInt() }
            .mapIndexed { index, length ->
                val value = if (index % 2 == 0) {
                    index / 2
                } else {
                    null
                }
                val type = if (index % 2 == 0) {
                    Type.USED
                } else {
                    Type.FREE
                }

                Block(value, type, length)
            }
            .filter { it.length > 0 }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
