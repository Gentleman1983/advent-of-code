package de.havox_design.aoc2018.day25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FourDimensionalAdventureTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, FourDimensionalAdventure.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day25/day25sample1.txt", 2L),
                Arguments.of("de/havox_design/aoc2018/day25/day25sample2.txt", 4L),
                Arguments.of("de/havox_design/aoc2018/day25/day25sample3.txt", 3L),
                Arguments.of("de/havox_design/aoc2018/day25/day25sample4.txt", 8L)
        );
    }
}
