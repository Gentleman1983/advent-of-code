package de.havox_design.aoc2017.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day01Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, Day01.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 3L),
                Arguments.of("part1sample2.txt", 4L),
                Arguments.of("part1sample3.txt", 0L),
                Arguments.of("part1sample4.txt", 9L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, Day01.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("part2sample1.txt", 6L),
                Arguments.of("part2sample2.txt", 0L),
                Arguments.of("part2sample3.txt", 4L),
                Arguments.of("part2sample4.txt", 12L),
                Arguments.of("part2sample5.txt", 4L)
        );
    }
}
