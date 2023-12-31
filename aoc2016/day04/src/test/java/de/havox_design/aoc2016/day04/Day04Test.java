package de.havox_design.aoc2016.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day04Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, SecurityThroughObscurity.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("day04Part1sample1.txt", 123L),
                Arguments.of("day04Part1sample2.txt", 987L),
                Arguments.of("day04Part1sample3.txt", 404L),
                Arguments.of("day04Part1sample4.txt", 0L),
                Arguments.of("day04Part1sample5.txt", 1514L)
        );
    }
}
