package de.havox_design.aoc2018.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day05Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, int expectation) {
        Assertions.assertEquals(expectation, AlchemicalReduction.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day05/day05part1sample1.txt", 0),
                Arguments.of("de/havox_design/aoc2018/day05/day05part1sample2.txt", 0),
                Arguments.of("de/havox_design/aoc2018/day05/day05part1sample3.txt", 4),
                Arguments.of("de/havox_design/aoc2018/day05/day05part1sample4.txt", 6),
                Arguments.of("de/havox_design/aoc2018/day05/day05part1sample5.txt", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, AlchemicalReduction.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day05/day05part2sample.txt", 0L)
        );
    }
}
