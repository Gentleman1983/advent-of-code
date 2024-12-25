package de.havox_design.aoc2022.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class StateFastTest {
    @ParameterizedTest
    @MethodSource("getDataForTestStateFastHandleMinute")
    fun testStateFastHandleMinute(
        state: StateFast,
        expectedTimeLeft: Int,
        expectedRobotsOre: Int,
        expectedRobotsClay: Int,
        expectedRobotsObsidian: Int,
        expectedRobotsGeode: Int,
        expectedMoneyOre: Int,
        expectedMoneyClay: Int,
        expectedMoneyObsidian: Int,
        expectedMoneyGeode: Int
    ) {
        state.handleMinute()

        assertAll(
            { state.timeLeft.shouldBe(expectedTimeLeft) },
            { state.workersOre.shouldBe(expectedRobotsOre) },
            { state.workersClay.shouldBe(expectedRobotsClay) },
            { state.workersObsidian.shouldBe(expectedRobotsObsidian) },
            { state.workersGeode.shouldBe(expectedRobotsGeode) },
            { state.moneyOre.shouldBe(expectedMoneyOre) },
            { state.moneyClay.shouldBe(expectedMoneyClay) },
            { state.moneyObsidian.shouldBe(expectedMoneyObsidian) },
            { state.moneyGeode.shouldBe(expectedMoneyGeode) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastHeuristics")
    fun testStateFastHeuristics(state: StateFast, expectedResult: Int) =
        state.heuristicScore().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastCompare")
    fun testStateFastCompare(state: StateFast, compareToState: StateFast, expectedResult: Int) =
        state.compareTo(compareToState).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastIsBetterThan")
    fun testStateFastIsBetterThan(
        state: StateFast,
        compareToState: StateFast,
        expectedResultA: Boolean,
        expectedResultB: Boolean
    ) =
        assertAll(
            { state.isBetterThan(compareToState).shouldBe(expectedResultA) },
            { compareToState.isBetterThan(state).shouldBe(expectedResultB) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestStateFastHandleMinute(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    23,
                    1,
                    0,
                    0,
                    0,
                    1,
                    0,
                    0,
                    0
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastHeuristics(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    1
                ),
                Arguments.of(
                    StateFast(moneyOre = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyClay = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyObsidian = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyGeode = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0),
                    0
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersClay = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersObsidian = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersGeode = 1),
                    1
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    StateFast(),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyOre = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyClay = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyObsidian = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyGeode = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0),
                    1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersClay = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersObsidian = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersGeode = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 2),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersClay = 1),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersObsidian = 1),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersGeode = 1),
                    -1
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastIsBetterThan(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    StateFast(),
                    true,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyOre = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyClay = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyObsidian = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyGeode = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0),
                    true,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersClay = 1),
                    false,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersObsidian = 1),
                    false,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersGeode = 1),
                    false,
                    false
                )
            )
    }
}
