package de.havox_design.aoc2015.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day06Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedNumberOfNiceStrings) {
        Assertions.assertEquals(expectedNumberOfNiceStrings, HouseDecoratingContest.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 1000000),
                Arguments.of("part1sample2.txt", 1000),
                Arguments.of("part1sample3.txt", 999000),
                Arguments.of("part1sample4.txt", 999996),
                Arguments.of("part1sample5.txt", 998996)
        );
    }
}
