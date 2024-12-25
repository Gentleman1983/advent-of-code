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

class StateTest {
    @ParameterizedTest
    @MethodSource("getDataForTestStateHandleMinute")
    fun testStateHandleMinute(
        state: State,
        expectedTimeLeft: Int,
        expectedRobots: RobotWorkers,
        expectedMoney: RobotCurrency
    ) {
        state.handleMinute()

        assertAll(
            { state.timeLeft.shouldBe(expectedTimeLeft) },
            { state.workers.shouldBe(expectedRobots) },
            { state.money.shouldBe(expectedMoney) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestStateHeuristics")
    fun testStateHeuristics(state: State, expectedResult: Int) =
        state.heuristicScore().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateCompare")
    fun testStateCompare(state: State, compareToState: State, expectedResult: Int) =
        state.compareTo(compareToState).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateIsBetterThan")
    fun testStateIsBetterThan(state: State, compareToState: State, expectedResultA: Boolean, expectedResultB: Boolean) =
        assertAll(
            { state.isBetterThan(compareToState).shouldBe(expectedResultA) },
            { compareToState.isBetterThan(state).shouldBe(expectedResultB) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestStateHandleMinute(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    23,
                    RobotWorkers(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                )
            )

        @JvmStatic
        private fun getDataForTestStateHeuristics(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    0
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    1
                )
            )

        @JvmStatic
        private fun getDataForTestStateCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    State(),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(2, 0, 0, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 1, 0, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 0, 1, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 0, 0, 1)),
                    -1
                )
            )

        @JvmStatic
        private fun getDataForTestStateIsBetterThan(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    State(),
                    true,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    true,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    false,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    false,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    false,
                    false
                )
            )
    }
}
