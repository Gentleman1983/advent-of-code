package de.havox_design.aoc2023.day05

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d

data class Almanac(val seeds: List<Long>, val maps: List<List<Position3d<Long>>>) {
    fun seedsToLocation(): List<Long> =
        seeds
        .map { seed -> computeLocation(seed) }

    fun seedRangesToLowestLocation(): Long {
        var lowest = Long.MAX_VALUE
        val ranges = seeds
            .chunked(2)
            .map { it[0]..<it[0] + it[1] }

        for (range in ranges) {
            for (i in range.first..range.last) {
                val location = computeLocation(i)
                if (location < lowest) {
                    lowest = location
                }
            }
        }

        return lowest
    }

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
            val maps = mutableListOf<List<Position3d<Long>>>()
            var submap = mutableListOf<Position3d<Long>>()
            for (i in sublist.indices) {
                if ("map:" in sublist[i]) {
                    if (submap.isNotEmpty()) maps.add(submap)
                    submap = mutableListOf()
                } else {
                    submap.add(
                        Position3d(
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