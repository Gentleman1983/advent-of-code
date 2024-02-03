package de.havox_design.aoc2018.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day03Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectedConflicts) {
        Assertions.assertEquals(expectedConflicts, NoMatterHowYouSliceIt.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day03/day03sample1.txt", 0L),
                Arguments.of("de/havox_design/aoc2018/day03/day03sample2.txt", 4L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expectedClaim) {
        Assertions.assertEquals(expectedClaim, NoMatterHowYouSliceIt.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day03/day03sample1.txt", 123),
                Arguments.of("de/havox_design/aoc2018/day03/day03sample2.txt", 3)
        );
    }
}
