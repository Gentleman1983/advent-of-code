package de.havox_design.aoc2015.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PresentDeliveryTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedHousesVisited) {
        Assertions.assertEquals(expectedHousesVisited, PresentDelivery.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day03/day03Part1sample1.txt", 2),
                Arguments.of("de/havox_design/aoc2015/day03/day03Part1sample2.txt", 4),
                Arguments.of("de/havox_design/aoc2015/day03/day03Part1sample3.txt", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedHousesVisited) {
        Assertions.assertEquals(expectedHousesVisited, PresentDelivery.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day03/day03Part2sample1.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day03/day03Part2sample2.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day03/day03Part2sample3.txt", 11)
        );
    }
}
