package de.havox_design.aoc.utils.kotlin.helpers.tests

import org.junit.jupiter.api.Assertions
import java.math.BigInteger

fun Any.shouldBe(expectation: Any) = Assertions.assertEquals(expectation, this)

fun BigInteger.shouldBe(expectation: BigInteger) = Assertions.assertEquals(expectation, this)

fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
fun Boolean.shouldBe(expectation: Boolean, message: String) = Assertions.assertEquals(expectation, this, message)

fun Collection<*>.shouldBe(expectation: Collection<*>) = Assertions.assertEquals(expectation, this)
fun Collection<*>.shouldContainAll(expectation: Collection<*>) = Assertions.assertTrue(this.containsAll(expectation))
fun Collection<*>.shouldContainAll(expectation: Collection<*>, message: String) = Assertions.assertTrue(this.containsAll(expectation), message)

fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
fun Int.shouldBe(expectation: Int, message: String) = Assertions.assertEquals(expectation, this, message)

fun List<*>.shouldBe(expectation: List<*>) = Assertions.assertEquals(expectation, this)

fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)

fun Map<*, *>.shouldBe(expectation: Map<*, *>) = Assertions.assertEquals(expectation, this)

fun Set<*>.shouldBe(expectation: Set<*>) = Assertions.assertEquals(expectation, this)
fun Set<*>.shouldContainAll(expectation: Collection<*>) = Assertions.assertTrue(this.containsAll(expectation))

fun String.shouldBe(expectation: String) = Assertions.assertEquals(expectation, this)
