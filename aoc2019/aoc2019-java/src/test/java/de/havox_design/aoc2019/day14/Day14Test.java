package de.havox_design.aoc2019.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day14Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SpaceStoichiometry.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day14/day14part1sample2.txt", 31L),
                Arguments.of("de/havox_design/aoc2019/day14/day14part1sample2.txt", 165L),
                Arguments.of("de/havox_design/aoc2019/day14/day14part1sample3.txt", 13312L),
                Arguments.of("de/havox_design/aoc2019/day14/day14part1sample4.txt", 180697L),
                Arguments.of("de/havox_design/aoc2019/day14/day14part1sample5.txt", 2210736L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, SpaceStoichiometry.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day14/day14part2sample.txt", 0L)
        );
    }
}
