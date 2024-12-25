package de.havox_design.aoc2018.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MineCartMadnessTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, String expectation) {
        Assertions.assertEquals(expectation, MineCartMadness.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day13/day13part1sample.txt", "7,3")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, String expectation) {
        Assertions.assertEquals(expectation, MineCartMadness.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day13/day13part2sample.txt", "6,4")
        );
    }
}
