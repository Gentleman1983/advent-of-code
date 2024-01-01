package de.havox_design.aoc2015.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day04Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedSecretNumber) {
        Assertions.assertEquals(expectedSecretNumber, AdventCoins.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day04/day04Sample1.txt", 609043),
                Arguments.of("de/havox_design/aoc2015/day04/day04Sample2.txt", 1048970)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedSecretNumber) {
        Assertions.assertEquals(expectedSecretNumber, AdventCoins.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day04/day04Sample1.txt", 6742839),
                Arguments.of("de/havox_design/aoc2015/day04/day04Sample2.txt", 5714438)
        );
    }
}
