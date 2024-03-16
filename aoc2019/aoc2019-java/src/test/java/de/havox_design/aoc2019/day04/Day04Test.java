package de.havox_design.aoc2019.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day04Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SecureContainer.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day04/day04part1sample1.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day04/day04part1sample2.txt", 0L),
                Arguments.of("de/havox_design/aoc2019/day04/day04part1sample3.txt", 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SecureContainer.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day04/day04part2sample1.txt", 1L),
                Arguments.of("de/havox_design/aoc2019/day04/day04part2sample2.txt", 0L),
                Arguments.of("de/havox_design/aoc2019/day04/day04part2sample3.txt", 1L)
        );
    }
}
