package de.havox_design.aoc2019.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day05Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SunnyWithAChanceOfAsteroids.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day05/day05part1sample.txt", 1L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SunnyWithAChanceOfAsteroids.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample1.txt", 0L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample2.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample3.txt", 0L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample4.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample5.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample6.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day05/day05part2sample7.txt", 999L)
        );
    }
}
