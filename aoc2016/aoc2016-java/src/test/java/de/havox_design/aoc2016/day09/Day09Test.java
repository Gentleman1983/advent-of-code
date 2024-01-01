package de.havox_design.aoc2016.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day09Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, ExplosivesInCyberspace.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample1.txt", 6L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample2.txt", 7L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample3.txt", 9L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample4.txt", 11L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample5.txt", 6L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part1sample6.txt", 18L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, ExplosivesInCyberspace.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day09/day09Part2sample1.txt", 9L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part2sample2.txt", 20L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part2sample3.txt", 241920L),
                Arguments.of("de/havox_design/aoc2016/day09/day09Part2sample4.txt", 445L)
        );
    }
}
