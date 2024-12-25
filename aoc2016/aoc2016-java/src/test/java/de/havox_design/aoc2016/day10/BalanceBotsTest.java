package de.havox_design.aoc2016.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BalanceBotsTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int low, int high) {
        BalanceBots instanceUnderTest = new BalanceBots(fileName);

        Assertions.assertEquals(expected, instanceUnderTest.solvePart1(low, high));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day10/day10Sample.txt", 2L, 2, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, BalanceBots.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day10/day10Sample.txt", 30L)
        );
    }
}
