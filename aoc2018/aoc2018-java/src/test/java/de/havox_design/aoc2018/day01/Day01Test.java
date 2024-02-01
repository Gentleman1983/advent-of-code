package de.havox_design.aoc2018.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day01Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectedFloor) {
        Assertions.assertEquals(expectedFloor, ChronalCalibration.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day01/day01part1sample1.txt", 3L),
                Arguments.of("de/havox_design/aoc2018/day01/day01part1sample2.txt", 3L),
                Arguments.of("de/havox_design/aoc2018/day01/day01part1sample3.txt", 0L),
                Arguments.of("de/havox_design/aoc2018/day01/day01part1sample4.txt", -6L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectedStep) {
        Assertions.assertEquals(expectedStep, ChronalCalibration.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day01/day01part2sample.txt", 0L)
        );
    }
}
