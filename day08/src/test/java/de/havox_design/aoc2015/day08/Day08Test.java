package de.havox_design.aoc2015.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day08Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedResult, int expectedNumberOfCharactersOfCode, int expectedNumberOfCharactersInMemory) {
        Matchsticks objectUnderTest = new Matchsticks(fileName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedNumberOfCharactersOfCode, objectUnderTest.calculateCharactersOfCode()),
                () -> Assertions.assertEquals(expectedNumberOfCharactersInMemory, objectUnderTest.calculateCharactersInMemory()),
                () -> Assertions.assertEquals(expectedResult, objectUnderTest.solvePart1())
        );
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("part1sample1.txt", 2, 2, 0),
                Arguments.of("part1sample2.txt", 2, 5, 3),
                Arguments.of("part1sample3.txt", 3, 10, 7),
                Arguments.of("part1sample4.txt", 5, 6, 1),
                Arguments.of("part1sample5.txt", 12, 23, 11)
        );
    }
}
