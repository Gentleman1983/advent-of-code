package de.havox_design.aoc20xx.day07

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day07Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, Day07.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc20xx/day07/day07part1sample.txt", 1L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, Day07.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc20xx/day07/day07part2sample.txt", 1L)
        );
    }
}