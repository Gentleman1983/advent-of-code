package de.havox_design.aoc2015.day04;

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
    void testPart1(String fileName, int expectedSecretNumber) {
        Assertions.assertEquals(expectedSecretNumber, AdventCoins.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("sample1.txt", 609043),
                Arguments.of("sample2.txt", 1048970)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedSecretNumber) {
        Assertions.assertEquals(expectedSecretNumber, AdventCoins.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("sample1.txt", 6742839),
                Arguments.of("sample2.txt", 5714438)
        );
    }
}
