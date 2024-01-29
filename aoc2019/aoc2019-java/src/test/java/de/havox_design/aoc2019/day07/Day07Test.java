package de.havox_design.aoc2019.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day07Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectedFloor) {
        Assertions.assertEquals(expectedFloor, Day07.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day07/day07part1sample.txt", 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectedStep) {
        Assertions.assertEquals(expectedStep, Day07.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day07/day07part2sample.txt", 0L)
        );
    }
}
