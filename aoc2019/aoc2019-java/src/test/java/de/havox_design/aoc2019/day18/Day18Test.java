package de.havox_design.aoc2019.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day18Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ManyWorldsInterpretation.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day18/day18part1sample1.txt", 8L),
                Arguments.of("de/havox_design/aoc2019/day18/day18part1sample2.txt", 86L),
                Arguments.of("de/havox_design/aoc2019/day18/day18part1sample3.txt", 132L),
                Arguments.of("de/havox_design/aoc2019/day18/day18part1sample4.txt", 136L),
                Arguments.of("de/havox_design/aoc2019/day18/day18part1sample5.txt", 81L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ManyWorldsInterpretation.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day18/day18part2sample.txt", 0L)
        );
    }
}
