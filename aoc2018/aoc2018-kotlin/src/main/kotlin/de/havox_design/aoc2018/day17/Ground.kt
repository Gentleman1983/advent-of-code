package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped

typealias Ground = List<Layer>

private fun getNeighbor(point: Coordinate, direction: FourDirectionsFlipped) =
    Coordinate(point.x + direction.dx(), point.y + direction.dy())

fun Ground.flow(boundary: Boundary, start: Coordinate) {
    val toProcess = ArrayDeque<Coordinate>()
        .apply { add(start) }

    while (toProcess.isNotEmpty()) {
        val now = toProcess.removeFirst()
        val south = getNeighbor(now, FourDirectionsFlipped.DOWN)

        if (!boundary.contains(south) || isFlowing(boundary, toProcess, south) { it }) {
            continue
        }

        val veins = this[south.y]
            .tokens()
            .map { it.point }
        val stillWater = this[south.y]
            .additions
            .filterIsInstance<StillWater>()
            .map { it.point }

        if (veins.any { it == south } || stillWater.any { it == south }) {
            val flowEast = isFlowing(boundary, toProcess, getNeighbor(now, FourDirectionsFlipped.RIGHT)) { it }
            val flowWest = isFlowing(boundary, toProcess, getNeighbor(now, FourDirectionsFlipped.LEFT)) { it }

            if (flowEast || flowWest) {
                continue
            }
        }

        if (hasSideWalls(boundary, now)) {
            fillHorizontal(now)
        }
    }
}

fun Ground.isFlowing(
    boundary: Boundary,
    toProcess: ArrayDeque<Coordinate>,
    point: Coordinate,
    toPrevious: (Coordinate) -> Coordinate
): Boolean {
    return if (boundary.contains(point) && this[point.y].nothingAt(point)) {
        this[point.y]
            .additions
            .add(FlowingWater(point))
        toProcess.addFirst(toPrevious(point))
        toProcess.addFirst(point)

        true
    } else {
        false
    }
}

fun Ground.hasSideWalls(boundary: Boundary, point: Coordinate): Boolean =
    hasSideWall(boundary, point) { getNeighbor(it, FourDirectionsFlipped.RIGHT) } &&
            hasSideWall(boundary, point) { getNeighbor(it, FourDirectionsFlipped.LEFT) }

fun Ground.hasSideWall(
    boundary: Boundary,
    point: Coordinate,
    toNext: (Coordinate) -> Coordinate
): Boolean {
    var now = point

    while (boundary.contains(now)) {
        with(this[now.y]) {
            when (now) {
                in tokens().map { it.point } -> return true
                in additions.map { it.point } -> now = toNext(now)
                else -> return false
            }
        }
    }

    return false
}

fun Ground.fillHorizontal(point: Coordinate) {
    fill(point) { getNeighbor(it, FourDirectionsFlipped.RIGHT) }
    fill(point) { getNeighbor(it, FourDirectionsFlipped.LEFT) }
}

fun Ground.fill(point: Coordinate, toNext: (Coordinate) -> Coordinate) {
    var now = point

    with(this[now.y]) {
        while (tokens().none { it.point == now }) {
            additions.add(StillWater(now))
            now = toNext(now)
        }
    }
}
