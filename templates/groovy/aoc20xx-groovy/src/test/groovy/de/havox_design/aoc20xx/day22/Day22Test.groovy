package de.havox_design.aoc20xx.day22

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day22Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, Day22.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc20xx/day22/day22part1sample.txt", 1L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, Day22.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc20xx/day22/day22part2sample.txt", 1L)
        );
    }
}