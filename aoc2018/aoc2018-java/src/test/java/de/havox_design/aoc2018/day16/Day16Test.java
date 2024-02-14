package de.havox_design.aoc2018.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day16Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, ChronalClassification.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day16/day16sample.txt", 1L)
        );
    }
}
