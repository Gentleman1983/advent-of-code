package de.havox_design.aoc2016.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day07Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, InternetProtocolVersion7.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("day07Part1sample1.txt", 1L),
                Arguments.of("day07Part1sample2.txt", 0L),
                Arguments.of("day07Part1sample3.txt", 0L),
                Arguments.of("day07Part1sample4.txt", 1L),
                Arguments.of("day07Part1sample5.txt", 2L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, InternetProtocolVersion7.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("day07Part2sample1.txt", 1L),
                Arguments.of("day07Part2sample2.txt", 0L),
                Arguments.of("day07Part2sample3.txt", 1L),
                Arguments.of("day07Part2sample4.txt", 1L),
                Arguments.of("day07Part2sample5.txt", 3L)
        );
    }
}
