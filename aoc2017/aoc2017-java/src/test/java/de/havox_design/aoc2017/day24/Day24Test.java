package de.havox_design.aoc2017.day24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day24Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, ElectromagneticMoat.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day24/day24Sample.txt", 31L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, ElectromagneticMoat.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day24/day24Sample.txt", 19L)
        );
    }
}
