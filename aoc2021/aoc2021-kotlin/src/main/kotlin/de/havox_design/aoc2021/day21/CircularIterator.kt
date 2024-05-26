package de.havox_design.aoc2021.day21

class CircularIterator<T>(private val iterable: Iterable<T>) : Iterator<T> {
    var iterator = iterable.iterator()

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): T {
        if (!iterator.hasNext()) {
            iterator = iterable.iterator()
        }

        return iterator.next()
    }
}
