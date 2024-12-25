package de.havox_design.aoc2015.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ReindeerOlympicsTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedDistance, int raceTime) {
        Assertions.assertEquals(expectedDistance, ReindeerOlympics.race(fileName, raceTime, false));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample1.txt", 1120, 1000),
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample2.txt", 1056, 1000),
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample3.txt", 1120, 1000)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedPoints, int raceTime) {
        Assertions.assertEquals(expectedPoints, ReindeerOlympics.race(fileName, raceTime, true));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample1.txt", 1000, 1000),
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample2.txt", 1000, 1000),
                Arguments.of("de/havox_design/aoc2015/day14/day14Sample3.txt", 689, 1000)
        );
    }
}
