package de.havox_design.aoc2015.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day09Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedShortestDistance) {
        Assertions.assertEquals(expectedShortestDistance, AllInASingleNight.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample.txt", 605)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedLongestDistance) {
        Assertions.assertEquals(expectedLongestDistance, AllInASingleNight.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("sample.txt", 982)
        );
    }
}
