package de.havox_design.aoc2015.day07;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day07Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[0]);
    }

    @ParameterizedTest
    @MethodSource ("getDataForPart1")
    void testPart1(String fileName, int expectedNumberOfLights) {
        Assertions.assertEquals(expectedNumberOfLights, LogicGates.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
            Arguments.of("part1sample1.txt", 72),
            Arguments.of("part1sample2.txt", 507),
            Arguments.of("part1sample3.txt", 492),
            Arguments.of("part1sample4.txt", 114),
            Arguments.of("part1sample5.txt", 65412),
            Arguments.of("part1sample6.txt", 65079),
            Arguments.of("part1sample7.txt", 123),
            Arguments.of("part1sample8.txt", 456)
        );
    }
}
