package de.havox_design.aoc2017.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day20Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, Day20.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, Day20.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("part2sample1.txt", 0L)
        );
    }
}
