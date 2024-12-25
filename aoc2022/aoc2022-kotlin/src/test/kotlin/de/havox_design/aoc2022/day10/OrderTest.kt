package de.havox_design.aoc2022.day10

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OrderTest {
    @ParameterizedTest
    @MethodSource("getDataForTestFindOrderByOrderName")
    fun testFindOrderByOrderName(orderName: String, expectedOrder: Order) =
        Order.findByOrderName(orderName).shouldBe(expectedOrder)

    companion object {
        @JvmStatic
        private fun getDataForTestFindOrderByOrderName(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Order.NOOP.orderName, Order.NOOP),
                Arguments.of(Order.ADDX.orderName, Order.ADDX),
                Arguments.of(Order.UNKNOWN.orderName, Order.UNKNOWN),
                Arguments.of("foo", Order.UNKNOWN),
                Arguments.of("bar", Order.UNKNOWN)
            )
    }
}
