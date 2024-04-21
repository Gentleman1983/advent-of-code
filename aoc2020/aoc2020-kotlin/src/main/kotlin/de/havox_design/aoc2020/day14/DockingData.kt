package de.havox_design.aoc2020.day14

import kotlin.math.pow

class DockingData(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val memory = mutableMapOf<Int, Long>()
        lateinit var mask: Mask

        data
            .forEach { line ->
                if (line.startsWith(ID_MASK)) {
                    val maskPattern = line
                        .substringAfter(PREFIX_MASK)
                    val orMask = maskPattern
                        .replace(ICON_UNKNOWN, ICON_ZERO)
                    val andMask = maskPattern
                        .replace(ICON_UNKNOWN, ICON_ONE)

                    mask = Mask(orMask, andMask)
                } else {
                    val value = line
                        .substringAfter(ICON_EQUALS)
                        .trim()
                        .toLong()
                    val address = line
                        .substring(line.indexOf(ICON_BRACKET_OPEN) + 1, line.indexOf(ICON_BRACKET_CLOSE))
                        .toInt()

                    memory[address] = mask.apply(value)
                }
            }
        return memory
            .values
            .sum()
    }

    fun processPart2(): Any {
        val memory = mutableMapOf<Long, Long>()
        var masks: List<Mask> = emptyList()

        data
            .forEach { line ->
                if (line.startsWith(ID_MASK)) {
                    val maskPattern = line
                        .substringAfter(PREFIX_MASK)

                    masks = maskPermutations(maskPattern)
                } else {
                    val value = line
                        .substringAfter(ICON_EQUALS)
                        .trim()
                        .toLong()
                    val baseAddress = line
                        .substring(line.indexOf(ICON_BRACKET_OPEN) + 1, line.indexOf(ICON_BRACKET_CLOSE))
                        .toLong()

                    masks
                        .forEach { mask ->
                            val address = mask
                                .apply(baseAddress)

                            memory[address] = value
                        }
                }
            }

        return memory
            .values
            .sum()
    }

    private fun maskPermutations(baseMask: String): List<Mask> {
        val xIndices = baseMask
            .mapIndexed { index, char -> Pair(index, char) }
            .filter { (_, char) -> char == ICON_CHAR_UNKNOWN }
            .mapIndexed { xIndex, (stringIndex, _) -> Pair(stringIndex, xIndex) }
            .toMap()
        val xCount = xIndices
            .size
        val maxXMask = RADIX_BINARY
            .toDouble()
            .pow(xCount)
            .toLong()
        val baseAndMask = ICON_ONE
            .repeat(baseMask.length)

        return (0 until maxXMask).map { value ->
            val xValues = value
                .toString(RADIX_BINARY)
                .padStart(xCount, ICON_CHAR_ZERO)
            val orMask = baseMask
                .toMutableList()
            val andMask = baseAndMask
                .toMutableList()

            xIndices
                .forEach { (stringIndex, xIndex) ->
                    orMask[stringIndex] = xValues[xIndex]
                    andMask[stringIndex] = xValues[xIndex]
                }

            Mask(orMask.joinToString(ICON_EMPTY), andMask.joinToString(ICON_EMPTY))
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ID_MASK = "mask"
        private const val ICON_CHAR_ZERO = '0'
        private const val ICON_CHAR_UNKNOWN = 'X'
        private const val ICON_EMPTY = ""
        private const val ICON_EQUALS = "="
        private const val ICON_ONE = "1"
        private const val ICON_UNKNOWN = "X"
        private const val ICON_BRACKET_CLOSE = "]"
        private const val ICON_BRACKET_OPEN = "["
        private const val ICON_ZERO = "0"
        private const val PREFIX_MASK = "mask = "
        private const val RADIX_BINARY = 2
    }
}
