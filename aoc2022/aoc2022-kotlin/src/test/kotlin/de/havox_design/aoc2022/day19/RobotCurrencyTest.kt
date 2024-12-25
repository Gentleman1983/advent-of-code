package de.havox_design.aoc2022.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RobotCurrencyTest {
    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyPlus")
    fun testCurrencyPlus(currencyA: RobotCurrency, currencyB: RobotCurrency, expectedResult: RobotCurrency) =
        currencyA.plus(currencyB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyMinus")
    fun testCurrencyMinus(currencyA: RobotCurrency, currencyB: RobotCurrency, expectedResult: RobotCurrency) =
        currencyA.minus(currencyB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyCompare")
    fun testCurrencyCompare(currency: RobotCurrency, compareToCurrency: RobotCurrency, expectedResult: Int) =
        currency.compareTo(compareToCurrency).shouldBe(expectedResult)

    @Test
    fun verifyEqualsContractOnRobotCurrencyClass() =
        EqualsVerifier
            .simple()
            .forClass(RobotCurrency::class.java)
            .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
            .verify()

    companion object {
        @JvmStatic
        private fun getDataForTestCurrencyPlus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(2, 0, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 2, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 2, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 2)
                ),
                Arguments.of(
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(8, 7, 6, 5),
                    RobotCurrency(9, 9, 9, 9)
                )
            )

        @JvmStatic
        private fun getDataForTestCurrencyMinus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(2, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 2, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 2, 0),
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 2),
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1)
                ),
                Arguments.of(
                    RobotCurrency(9, 9, 9, 9),
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(8, 7, 6, 5)
                )
            )

        @JvmStatic
        private fun getDataForTestCurrencyCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1),
                    0
                ),
                Arguments.of(
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(1, 2, 3, 4),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 99, 99, 99),
                    RobotCurrency(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 0, 99, 99),
                    RobotCurrency(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 0, 99),
                    RobotCurrency(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 0),
                    RobotCurrency(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(1, 99, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 2, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 3, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 4),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                )
            )
    }
}
