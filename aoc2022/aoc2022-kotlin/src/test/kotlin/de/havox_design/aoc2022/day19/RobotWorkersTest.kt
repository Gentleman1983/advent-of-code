package de.havox_design.aoc2022.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RobotWorkersTest {
    @ParameterizedTest
    @MethodSource("getDataForTestWorkersProduction")
    fun testWorkersProduction(worker: RobotWorkers, expectedResult: RobotCurrency) =
        worker.calculateResourceProduction().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestWorkersPlus")
    fun testWorkersPlus(workerA: RobotWorkers, workerB: RobotWorkers, expectedResult: RobotWorkers) =
        workerA.plus(workerB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestWorkersCompare")
    fun testWorkersCompare(worker: RobotWorkers, compareToWorker: RobotWorkers, expectedResult: Int) =
        worker.compareTo(compareToWorker).shouldBe(expectedResult)

    @Test
    fun verifyEqualsContractOnRobotWorkersClass() =
        EqualsVerifier
            .simple()
            .forClass(RobotWorkers::class.java)
            .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
            .verify()

    companion object {
        @JvmStatic
        private fun getDataForTestWorkersProduction(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(1, 1, 1, 1),
                    RobotCurrency(1, 1, 1, 1)
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 17, 23),
                    RobotCurrency(1, 0, 17, 23)
                ),
                Arguments.of(
                    RobotWorkers(5, 4, 3, 2),
                    RobotCurrency(5, 4, 3, 2)
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1)
                )
            )

        @JvmStatic
        private fun getDataForTestWorkersPlus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(2, 0, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 2, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 2, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 2)
                ),
                Arguments.of(
                    RobotWorkers(1, 2, 3, 4),
                    RobotWorkers(8, 7, 6, 5),
                    RobotWorkers(9, 9, 9, 9)
                )
            )

        @JvmStatic
        private fun getDataForTestWorkersCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 1, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 1),
                    0
                ),
                Arguments.of(
                    RobotWorkers(1, 2, 3, 4),
                    RobotWorkers(1, 2, 3, 4),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 99, 99, 99),
                    RobotWorkers(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 0, 99, 99),
                    RobotWorkers(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 0, 99),
                    RobotWorkers(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 0),
                    RobotWorkers(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(1, 99, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 2, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 3, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 4),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                )
            )
    }
}
