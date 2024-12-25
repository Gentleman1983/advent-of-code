package de.havox_design.aoc2018.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MarbleManiaTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, MarbleMania.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample1.txt", 32L),
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample2.txt", 8317L),
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample3.txt", 146373L),
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample4.txt", 2764L),
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample5.txt", 54718L),
                Arguments.of("de/havox_design/aoc2018/day09/day09part1sample6.txt", 37305L)
        );
    }
}
