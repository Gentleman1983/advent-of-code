package de.havox_design.aoc2015.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day10Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedResultLength) {
        Assertions.assertEquals(expectedResultLength, Elves.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample1.txt", 82350),
                Arguments.of("sample2.txt", 107312),
                Arguments.of("sample3.txt", 139984),
                Arguments.of("sample4.txt", 182376),
                Arguments.of("sample5.txt", 237746)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedResultLength) {
        Assertions.assertEquals(expectedResultLength, Elves.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("sample1.txt", 1166642),
                Arguments.of("sample2.txt", 1520986),
                Arguments.of("sample3.txt", 1982710),
                Arguments.of("sample4.txt", 2584304),
                Arguments.of("sample5.txt", 3369156)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForProcessData")
    void testProcessData(String fileName, String expectedString, int iterations) {
        Elves instanceUnderTest = new Elves(fileName);
        Assertions.assertEquals(expectedString, instanceUnderTest.processForIterations(iterations));
    }

    private static Stream<Arguments> getDataForProcessData() {
        return Stream.of(
                Arguments.of("sample1.txt", "11", 1),
                Arguments.of("sample1.txt", "21", 2),
                Arguments.of("sample1.txt", "1211", 3),
                Arguments.of("sample1.txt", "111221", 4),
                Arguments.of("sample1.txt", "312211", 5),
                Arguments.of("sample2.txt", "21", 1),
                Arguments.of("sample2.txt", "1211", 2),
                Arguments.of("sample2.txt", "111221", 3),
                Arguments.of("sample2.txt", "312211", 4),
                Arguments.of("sample3.txt", "1211", 1),
                Arguments.of("sample3.txt", "111221", 2),
                Arguments.of("sample3.txt", "312211", 3),
                Arguments.of("sample4.txt", "111221", 1),
                Arguments.of("sample4.txt", "312211", 2),
                Arguments.of("sample5.txt", "312211", 1)
        );
    }
}
