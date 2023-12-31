package de.havox_design.aoc2016.day08;

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
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, TwoFactorAuthentication.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("day08Part1sample1.txt", 6L),
                Arguments.of("day08Part1sample2.txt", 6L),
                Arguments.of("day08Part1sample3.txt", 6L),
                Arguments.of("day08Part1sample4.txt", 6L)
        );
    }
}
