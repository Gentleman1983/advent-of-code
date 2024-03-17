package de.havox_design.aoc2019.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day10Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, Day10.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day10/day10part1sample1.txt", 8L),
                Arguments.of("de/havox_design/aoc2019/day10/day10part1sample2.txt", 33L),
                Arguments.of("de/havox_design/aoc2019/day10/day10part1sample3.txt", 35L),
                Arguments.of("de/havox_design/aoc2019/day10/day10part1sample4.txt", 41L),
                Arguments.of("de/havox_design/aoc2019/day10/day10part1sample5.txt", 210L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, Day10.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day10/day10part2sample.txt", 0L)
        );
    }
}
