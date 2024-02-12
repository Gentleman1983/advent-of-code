package de.havox_design.aoc2018.day15;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day15Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, int expectation) {
        Assertions.assertEquals(expectation, BeverageBandits.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day15/day15sample1.txt", 27730),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample2.txt", 36334),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample3.txt", 39514),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample4.txt", 27755),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample5.txt", 28944),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample6.txt", 18740)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expectation) {
        Assertions.assertEquals(expectation, BeverageBandits.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day15/day15sample1.txt", 4988),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample2.txt", 29064),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample3.txt", 31284),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample4.txt", 3478),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample5.txt", 6474),
                Arguments.of("de/havox_design/aoc2018/day15/day15sample6.txt", 1140)
        );
    }

    @Test
    void testGameMapContracts() {
        EqualsVerifier.forClass(GameMap.class).verify();
    }
}
