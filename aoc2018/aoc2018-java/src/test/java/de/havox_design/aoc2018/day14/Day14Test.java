package de.havox_design.aoc2018.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day14Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, String expectation) {
        Assertions.assertEquals(expectation, ChocolateCharts.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day14/day14part1sample1.txt", "5158916779"),
                Arguments.of("de/havox_design/aoc2018/day14/day14part1sample2.txt", "0124515891"),
                Arguments.of("de/havox_design/aoc2018/day14/day14part1sample3.txt", "9251071085"),
                Arguments.of("de/havox_design/aoc2018/day14/day14part1sample4.txt", "5941429882")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ChocolateCharts.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day14/day14part2sample.txt", 0L)
        );
    }
}
