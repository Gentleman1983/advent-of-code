package de.havox_design.aoc2015.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day05Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedNumberOfNiceStrings) {
        Assertions.assertEquals(expectedNumberOfNiceStrings, NiceStrings.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample2.txt", 1),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample3.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample4.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample5.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part1sample6.txt", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedNumberOfNiceStrings) {
        Assertions.assertEquals(expectedNumberOfNiceStrings, NiceStrings.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day05/day05Part2sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part2sample2.txt", 1),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part2sample3.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part2sample4.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day05/day05Part2sample5.txt", 2)
        );
    }
}
