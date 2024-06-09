package de.havox_design.aoc2021.day19

import de.havox_design.aoc.utils.kotlin.helpers.parseInts
import de.havox_design.aoc.utils.kotlin.model.positions.*

class BeaconScanner(private var filename: String) {
    private val ALL_ROTATIONS: List<Position3d<Int>.() -> Position3d<Int>> =
        listOf(
            { Position3d(x, y, z) },
            { Position3d(x, y, -z) },
            { Position3d(x, -y, z) },
            { Position3d(x, -y, -z) },
            { Position3d(-x, y, z) },
            { Position3d(-x, y, -z) },
            { Position3d(-x, -y, z) },
            { Position3d(-x, -y, -z) },
            { Position3d(x, z, y) },
            { Position3d(x, z, -y) },
            { Position3d(x, -z, y) },
            { Position3d(x, -z, -y) },
            { Position3d(-x, z, y) },
            { Position3d(-x, z, -y) },
            { Position3d(-x, -z, y) },
            { Position3d(-x, -z, -y) },
            { Position3d(y, x, z) },
            { Position3d(y, x, -z) },
            { Position3d(y, -x, z) },
            { Position3d(y, -x, -z) },
            { Position3d(-y, x, z) },
            { Position3d(-y, x, -z) },
            { Position3d(-y, -x, z) },
            { Position3d(-y, -x, -z) },
            { Position3d(y, z, x) },
            { Position3d(y, z, -x) },
            { Position3d(y, -z, x) },
            { Position3d(y, -z, -x) },
            { Position3d(-y, z, x) },
            { Position3d(-y, z, -x) },
            { Position3d(-y, -z, x) },
            { Position3d(-y, -z, -x) },
            { Position3d(z, y, x) },
            { Position3d(z, y, -x) },
            { Position3d(z, -y, x) },
            { Position3d(z, -y, -x) },
            { Position3d(-z, y, x) },
            { Position3d(-z, y, -x) },
            { Position3d(-z, -y, x) },
            { Position3d(-z, -y, -x) },
            { Position3d(z, x, y) },
            { Position3d(z, x, -y) },
            { Position3d(z, -x, y) },
            { Position3d(z, -x, -y) },
            { Position3d(-z, x, y) },
            { Position3d(-z, x, -y) },
            { Position3d(-z, -x, y) },
            { Position3d(-z, -x, -y) },
        )

    private val DELIMITER_COORDINATES = ","
    private val DELIMITER_SCANNERS = "\n\n"
    private val ICON_CARRIAGE_RETURN = "\r"
    private val ICON_EMPTY = ""
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        findAllData()
            .first
            .size

    fun processPart2(): Any =
        findAllData()
            .second
            .toList()
            .pairs()
            .maxOf { (a, b) -> (a - b).manhattanDistance() }

    private fun findAllData(): Pair<Set<Position3d<Int>>, Set<Position3d<Int>>> {
        val visibleBeacons = data
            .replace(ICON_CARRIAGE_RETURN, ICON_EMPTY)
            .split(DELIMITER_SCANNERS)
            .map { block ->
                block
                    .lines()
                    .drop(1)
                    .map { it.parseInts(DELIMITER_COORDINATES) }
                    .map { Position3d(it[0], it[1], it[2]) }
            }
        val dists = visibleBeacons
            .map { beacons ->
                beacons
                    .flatMapIndexed { index, first ->
                        beacons.drop(index + 1).map { second ->
                            (second - first).length2()
                        }
                    }.toSet()
            }
        val possiblePairs = visibleBeacons.indices
            .toList()
            .pairs()
            .filter { (indexA, indexB) ->
                dists[indexA].intersect(dists[indexB]).size >= 66
            }
            .flatMap { listOf(it, it.second to it.first) }
            .groupBy { it.first }
        val oriented = mutableSetOf(0)
        val beacons = visibleBeacons[0].toMutableSet()
        val scanners = mutableSetOf(Position3d(0, 0, 0))
        val queue = ArrayDeque(possiblePairs[0].orEmpty())
        val orientedBeacons = visibleBeacons.toMutableList()

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            val (base, new) = when {
                curr.first in oriented && curr.second in oriented -> continue
                curr.first in oriented -> curr.first to curr.second
                curr.second in oriented -> curr.second to curr.first
                else -> error("One of points should be already oriented")
            }
            val baseDists = orientedBeacons[base]
                .indices
                .toList()
                .pairs()
                .map { (a, b) ->
                    val beaconA = orientedBeacons[base][a]
                    val beaconB = orientedBeacons[base][b]
                    Triple(a, b, (beaconB - beaconA).length2())
                }
                .toList()
            val newDists = visibleBeacons[new]
                .indices
                .toList()
                .pairs()
                .map { (a, b) ->
                    val beaconA = visibleBeacons[new][a]
                    val beaconB = visibleBeacons[new][b]
                    Triple(a, b, (beaconB - beaconA).length2())
                }
                .toList()
            val matchCount = mutableMapOf<Pair<Int, Int>, Int>()
            baseDists.forEach { baseTriple ->
                newDists.forEach { newTriple ->
                    if (baseTriple.third == newTriple.third) {
                        matchCount[baseTriple.first to newTriple.first] =
                            matchCount.getOrDefault(baseTriple.first to newTriple.first, 0) + 1
                        matchCount[baseTriple.first to newTriple.second] =
                            matchCount.getOrDefault(baseTriple.first to newTriple.second, 0) + 1
                        matchCount[baseTriple.second to newTriple.first] =
                            matchCount.getOrDefault(baseTriple.second to newTriple.first, 0) + 1
                        matchCount[baseTriple.second to newTriple.second] =
                            matchCount.getOrDefault(baseTriple.second to newTriple.second, 0) + 1
                    }
                }
            }

            val matches = matchCount.filterValues { it == 11 }.keys
            val rotation = ALL_ROTATIONS.first { currRotation ->
                matches
                    .map { (baseIndex, newIndex) ->
                        val baseA = orientedBeacons[base][baseIndex]
                        val newA = visibleBeacons[new][newIndex]
                        val convertedA = currRotation(newA)
                        baseA - convertedA
                    }
                    .distinct()
                    .size == 1
            }

            val delta = matches.first().let { (baseIndex, newIndex) ->
                val baseA = orientedBeacons[base][baseIndex]
                val newA = visibleBeacons[new][newIndex]
                val convertedA = rotation(newA)
                baseA - convertedA
            }
            val rotatedPoints = visibleBeacons[new]
                .map { point ->
                    rotation(point) + delta
                }

            beacons += rotatedPoints
            scanners += rotation(Position3d(0, 0, 0)) + delta
            orientedBeacons[new] = rotatedPoints
            oriented += new
            queue += possiblePairs[new].orEmpty()
        }

        return beacons to scanners
    }

    private fun <T : Any> List<T>.pairs(): Sequence<Pair<T, T>> {
        require(size > 1)

        return sequence {
            for (x in indices) {
                for (y in x + 1..<size) {
                    yield(get(x) to get(y))
                }
            }
        }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
