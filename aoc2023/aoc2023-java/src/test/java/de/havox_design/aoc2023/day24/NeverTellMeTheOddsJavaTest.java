package de.havox_design.aoc2023.day24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NeverTellMeTheOddsJavaTest {

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expectedStep) {
        Assertions.assertEquals(expectedStep, NeverTellMeTheOddsJava.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2023/day24/day24part2sample.txt", 47)
        );
    }
}
