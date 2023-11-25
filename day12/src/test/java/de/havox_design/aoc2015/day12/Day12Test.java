package de.havox_design.aoc2015.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day12Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedSum) {
        Assertions.assertEquals(expectedSum, JSONAbacus.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 6),
                Arguments.of("part1sample2.txt", 6),
                Arguments.of("part1sample3.txt", 3),
                Arguments.of("part1sample4.txt", 3),
                Arguments.of("part1sample5.txt", 0),
                Arguments.of("part1sample6.txt", 0),
                Arguments.of("part1sample7.txt", 0),
                Arguments.of("part1sample8.txt", 0)
        );
    }
}
