package de.havox_design.aoc2019.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day03Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, CrossedWires.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day03/day03sample1.txt", 6L),
                Arguments.of("de/havox_design/aoc2019/day03/day03sample2.txt", 159L),
                Arguments.of("de/havox_design/aoc2019/day03/day03sample3.txt", 135L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, CrossedWires.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day03/day03sample1.txt", 30L),
                Arguments.of("de/havox_design/aoc2019/day03/day03sample2.txt", 610L),
                Arguments.of("de/havox_design/aoc2019/day03/day03sample3.txt", 410L)
        );
    }
}
