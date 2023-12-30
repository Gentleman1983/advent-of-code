package de.havox_design.aoc2016.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day18Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int rowCount) {
        Day18 instanceUnderTest = new Day18(fileName);
        Assertions.assertEquals(expected, instanceUnderTest.solvePart1(rowCount));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample1.txt", 6L, 3),
                Arguments.of("sample2.txt", 38L, 10)
        );
    }
}
