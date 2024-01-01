package de.havox_design.aoc2016.day21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day21Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expected, String input) {
        Assertions.assertEquals(expected, ScrambledLettersAndHash.solvePart1(fileName, input));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day21/day21Sample.txt", "decab", "abcde")
        );
    }
}
