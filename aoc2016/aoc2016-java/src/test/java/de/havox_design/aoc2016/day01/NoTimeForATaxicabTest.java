package de.havox_design.aoc2016.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NoTimeForATaxicabTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, NoTimeForATaxicab.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day01/day01Part1sample1.txt", 5L),
                Arguments.of("de/havox_design/aoc2016/day01/day01Part1sample2.txt", 2L),
                Arguments.of("de/havox_design/aoc2016/day01/day01Part1sample3.txt", 12L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, NoTimeForATaxicab.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day01/day01Part2sample.txt", 4L)
        );
    }
}
