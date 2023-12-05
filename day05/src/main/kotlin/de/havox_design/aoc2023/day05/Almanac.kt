package de.havox_design.aoc2023.day05

data class Almanac(val seeds: List<Long>, val maps: List<List<Triple<Long, Long, Long>>>) {
    fun seedsToLocation(): List<Long> =
        seeds
        .map { seed -> computeLocation(seed) }

    private fun computeLocation(seed: Long): Long {
        var current = seed

        for (element in maps) {
            for (entry in element) {
                val (destStart, sourceStart, length) = entry
                if (current in sourceStart..<sourceStart + length) {
                    val delta = current - sourceStart
                    current = destStart + (delta)
                    break
                }
            }
        }
        return current
    }

    companion object {
        fun from(input: List<String>): Almanac {
            val seeds = input[0].substringAfter("seeds: ")
                .split(" ")
                .filter(String::isNotBlank)
                .map(String::toLong)
            val sublist = input.subList(1, input.size)
            val maps = mutableListOf<List<Triple<Long, Long, Long>>>()
            var submap = mutableListOf<Triple<Long, Long, Long>>()
            for (i in sublist.indices) {
                if ("map:" in sublist[i]) {
                    if (submap.isNotEmpty()) maps.add(submap)
                    submap = mutableListOf()
                } else {
                    submap.add(
                        Triple(
                            sublist[i].substringBefore(" ").toLong(),
                            sublist[i].substringAfter(" ").substringBefore(" ").toLong(),
                            sublist[i].substringAfterLast(" ").toLong()
                        )
                    )
                }
            }
            if (submap.isNotEmpty()) maps.add(submap)
            return Almanac(seeds, maps)
        }
    }
}