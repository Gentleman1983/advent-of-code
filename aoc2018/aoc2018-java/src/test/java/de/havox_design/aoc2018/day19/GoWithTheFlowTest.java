package de.havox_design.aoc2018.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GoWithTheFlowTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, GoWithTheFlow.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day19/day19sample.txt", 7L)
        );
    }
}
