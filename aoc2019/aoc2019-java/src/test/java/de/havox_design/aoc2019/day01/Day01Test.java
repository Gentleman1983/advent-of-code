package de.havox_design.aoc2019.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day01Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, Day01.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day01/day01sample1.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample2.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample3.txt", 654L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample4.txt", 33583L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample5.txt", 34241L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, Day01.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day01/day01sample1.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample2.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample3.txt", 966L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample4.txt", 50346L),
                Arguments.of("de/havox_design/aoc2019/day01/day01sample5.txt", 51316L)
        );
    }
}
