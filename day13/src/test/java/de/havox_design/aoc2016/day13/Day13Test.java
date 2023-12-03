package de.havox_design.aoc2016.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day13Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int startColumn, int startRow, int endColumn, int endRow) {
        Day13 instanceUnderTest = new Day13(fileName);
        Assertions.assertEquals(expected, instanceUnderTest.solvePart1(startColumn, startRow, endColumn, endRow));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample.txt", 11L, 1, 1, 7, 4)
        );
    }
}
