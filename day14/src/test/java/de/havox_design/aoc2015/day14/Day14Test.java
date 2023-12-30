package de.havox_design.aoc2015.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day14Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedDistance, int raceTime) {
        Assertions.assertEquals(expectedDistance, ReindeerOlympics.race(fileName, raceTime, false));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample1.txt", 1120, 1000),
                Arguments.of("sample2.txt", 1056, 1000),
                Arguments.of("sample3.txt", 1120, 1000)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedPoints, int raceTime) {
        Assertions.assertEquals(expectedPoints, ReindeerOlympics.race(fileName, raceTime, true));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("sample1.txt", 1000, 1000),
                Arguments.of("sample2.txt", 1000, 1000),
                Arguments.of("sample3.txt", 689, 1000)
        );
    }
}
