package de.havox_design.aoc2015.day02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day02Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedSquareFoots) {
        Assertions.assertEquals(expectedSquareFoots, WrappingPaper.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("day02Sample1.txt", 58),
                Arguments.of("day02Sample2.txt", 43),
                Arguments.of("day02Sample3.txt", 101)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedLengthOfRibbon) {
        Assertions.assertEquals(expectedLengthOfRibbon, WrappingPaper.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("day02Sample1.txt", 34),
                Arguments.of("day02Sample2.txt", 14),
                Arguments.of("day02Sample3.txt", 48)
        );
    }
}
