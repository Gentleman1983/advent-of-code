package de.havox_design.aoc2023.day12

import kotlin.streams.asStream

data class Record(val text: String, val groups: List<Int>) {
    private val ICON_BROKEN_SPRING = '#'
    private val ICON_OPERATIONAL_SPRING = '.'
    private val ICON_UNKNOWN_SPRING = '?'
    private val possibilities = replaceQuestionmark(sequenceOf(text), text.count { it == '?' })
    val possibleGroups = possibilities
        .asStream()
        .parallel()
        .map {
            it
                .split(ICON_OPERATIONAL_SPRING)
                .filter { it.isNotBlank() }
                .map { it.length }
        }
        .filter { it == groups }
    val possibilitiesCount = findSolutions(mutableMapOf(), "$text.".toList(), groups, 0)

    private tailrec fun replaceQuestionmark(lines: Sequence<String>, count: Int): Sequence<String> {
        if (count > 0) {
            return replaceQuestionmark(
                lines
                    .flatMap {
                        listOf(
                            it.replaceFirst(ICON_UNKNOWN_SPRING, ICON_OPERATIONAL_SPRING),
                            it.replaceFirst(ICON_UNKNOWN_SPRING, ICON_BROKEN_SPRING)
                        )
                    },
                count - 1
            )
        }
        return lines
    }

    @SuppressWarnings("kotlin:S6510", "kotlin:S3776")
    private fun findSolutions(
        cache: MutableMap<Triple<List<Char>, List<Int>, Int>, Long>,
        line: List<Char>,
        sizes: List<Int>,
        doneInGroup: Int
    ): Long {
        val cv = cache[Triple(line, sizes, doneInGroup)]

        when {
            cv != null -> {
                return cv
            }

            else -> when {
                line.isEmpty() -> {
                    return when {
                        sizes.isEmpty() && (doneInGroup == 0) -> 1
                        else -> 0
                    }
                }

                else -> {
                    var solutions = 0L
                    val possible = when {
                        line[0] == ICON_UNKNOWN_SPRING -> listOf(ICON_OPERATIONAL_SPRING, ICON_BROKEN_SPRING)
                        else -> line.subList(0, 1)
                    }

                    for (c in possible) {
                        when (c) {
                            '#' -> {
                                solutions += findSolutions(cache, line.subList(1, line.size), sizes, doneInGroup + 1)
                            }

                            else -> {
                                when {
                                    doneInGroup > 0 -> {
                                        when {
                                            sizes.isNotEmpty() && sizes[0] == doneInGroup -> solutions += findSolutions(
                                                cache,
                                                line.subList(1, line.size),
                                                sizes.subList(1, sizes.size),
                                                0
                                            )
                                        }
                                    }

                                    else -> {
                                        solutions += findSolutions(cache, line.subList(1, line.size), sizes, 0)
                                    }
                                }
                            }
                        }
                    }

                    cache[Triple(line, sizes, doneInGroup)] = solutions

                    return solutions
                }
            }
        }
    }
}
