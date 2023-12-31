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
        Assertions.assertEquals(expected, InverseCaptcha.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("day01Part1sample1.txt", 3L),
                Arguments.of("day01Part1sample2.txt", 4L),
                Arguments.of("day01Part1sample3.txt", 0L),
                Arguments.of("day01Part1sample4.txt", 9L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, InverseCaptcha.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("day01Part2sample1.txt", 6L),
                Arguments.of("day01Part2sample2.txt", 0L),
                Arguments.of("day01Part2sample3.txt", 4L),
                Arguments.of("day01Part2sample4.txt", 12L),
                Arguments.of("day01Part2sample5.txt", 4L)
        );
    }
}
