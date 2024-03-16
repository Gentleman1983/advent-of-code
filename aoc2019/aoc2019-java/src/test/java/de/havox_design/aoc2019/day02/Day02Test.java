package de.havox_design.aoc2019.day02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day02Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ProgramAlarm1202.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day02/day02part1sample1.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day02/day02part1sample2.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day02/day02part1sample3.txt", 2L),
                Arguments.of("de/havox_design/aoc2019/day02/day02part1sample4.txt", 30L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ProgramAlarm1202.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day02/day02part2sample.txt", 0L)
        );
    }
}
