package de.havox_design.aoc2016.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day03Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, SquaresWithThreeSides.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample1.txt", 0L),
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample2.txt", 1L),
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample3.txt", 1L),
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample4.txt", 0L),
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample5.txt", 0L),
                Arguments.of("de/havox_design/aoc2016/day03/day03Part1sample6.txt", 2L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, SquaresWithThreeSides.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day03/day03Part2sample.txt", 6L)
        );
    }
}
