package de.havox_design.aoc2017.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day22Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int numberOfIterations) {
        Assertions.assertEquals(expected, Day22.solvePart1(fileName, numberOfIterations));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample.txt", 5L, 7),
                Arguments.of("sample.txt", 41L, 70),
                Arguments.of("sample.txt", 5587L, 10000)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected, int numberOfIterations) {
        Assertions.assertEquals(expected, Day22.solvePart2(fileName, numberOfIterations));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("sample.txt", 26L, 100),
                Arguments.of("sample.txt", 2511944L, 10000000)
        );
    }
}
