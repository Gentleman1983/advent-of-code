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

fun List<Int>.sumWith(other: List<Int>) =
    zip(other, Int::plus)

fun Iterable<Int>.product() =
    reduce { acc, item -> acc * item }

@SuppressWarnings("kotlin:S6532")
fun <T : Comparable<T>> Iterable<T>.minAndMax(): Pair<T, T> {
    val iterator = iterator()
    if (!iterator.hasNext()) {
        throw IllegalArgumentException("Cannot get min and max of empty collection")
    }

    var min = iterator.next()
    var max = min

    while (iterator.hasNext()) {
        val next = iterator.next()

        when {
            next < min -> min = next
            next > max -> max = next
        }
    }

    return min to max
}

fun <K, V> Map<K, V>.pivot() =
    entries
        .associate { (key, value) -> value to key }

fun Iterable<Int>.digitsToInt(radix: Int) =
    reduce { acc, digit -> acc * radix + digit }

fun <A, B, R> Iterable<A>.cartesianProduct(other: Iterable<B>, transform: (A, B) -> R): List<R> =
    flatMap { a -> other.map { b -> transform(a, b) } }