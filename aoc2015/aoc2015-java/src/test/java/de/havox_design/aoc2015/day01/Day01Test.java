package de.havox_design.aoc2015.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day01Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, int expectedFloor) {
        Assertions.assertEquals(expectedFloor, NotQuiteLisp.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample1.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample2.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample3.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample4.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample5.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample6.txt", -1),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample7.txt", -1),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample8.txt", -3),
                Arguments.of("de/havox_design/aoc2015/day01/day01part1sample9.txt", -3)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expectedStep) {
        Assertions.assertEquals(expectedStep, NotQuiteLisp.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day01/day01part2sample1.txt", 1),
                Arguments.of("de/havox_design/aoc2015/day01/day01part2sample2.txt", 5)
        );
    }
}
