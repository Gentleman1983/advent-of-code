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
        Assertions.assertEquals(expectedDistance, ReindeerOlympics.race(fileName, raceTime));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 1120, 1000),
                Arguments.of("part1sample2.txt", 1056, 1000),
                Arguments.of("part1sample3.txt", 1120, 1000)
        );
    }
}
