package de.havox_design.aoc2020.day14

class DockingData(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val memory = mutableMapOf<Int, Long>()
        lateinit var mask: Mask

        data.forEach { line ->
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

    fun processPart2(): Any =
        0L

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val ID_MASK = "mask"
        private const val ICON_EQUALS = "="
        private const val ICON_ONE = "1"
        private const val ICON_UNKNOWN = "X"
        private const val ICON_BRACKET_CLOSE = "]"
        private const val ICON_BRACKET_OPEN = "["
        private const val ICON_ZERO = "0"
        private const val PREFIX_MASK = "mask = "
    }
}
