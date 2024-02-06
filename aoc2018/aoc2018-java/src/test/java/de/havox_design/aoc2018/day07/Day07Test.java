package de.havox_design.aoc2018.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class Day07Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, String expectation) {
        Assertions.assertEquals(expectation, Day07.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day07/day07sample.txt", "CABDFE")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation, int workers, Map<String, Integer> processingTime) {
        Assertions.assertEquals(expectation, Day07.processTask2(fileName, workers, processingTime));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of(
                        "de/havox_design/aoc2018/day07/day07sample.txt",
                        15L,
                        2,
                        Map.of(
                                "A", 1,
                                "B", 2,
                                "C", 3,
                                "D", 4,
                                "E", 5,
                                "F", 6
                        )
                )
        );
    }
}
