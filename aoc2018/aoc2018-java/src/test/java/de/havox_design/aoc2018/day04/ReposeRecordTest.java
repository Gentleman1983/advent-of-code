package de.havox_design.aoc2018.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ReposeRecordTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, int expectation) {
        Assertions.assertEquals(expectation, ReposeRecord.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day04/day04sample.txt", 240)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expectation) {
        Assertions.assertEquals(expectation, ReposeRecord.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day04/day04sample.txt", 4455)
        );
    }
}
