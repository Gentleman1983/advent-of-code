package de.havox_design.aoc.utils.kotlin.helpers

import java.util.concurrent.BlockingQueue
import kotlin.math.absoluteValue

fun <T> List<T>.toPair(): Pair<T, T> =
    Pair(get(0), get(1))

val IntArray.abs: Int
    get() =
        sumOf { it.absoluteValue }

fun IntArray.mapToInt(transform: (Int) -> Int): IntArray =
    IntArray(this.size) { transform(this[it]) }

fun <T> BlockingQueue<T>.drainToList(): List<T> {
    val outputList = mutableListOf<T>()

    drainTo(outputList)

    return outputList
}