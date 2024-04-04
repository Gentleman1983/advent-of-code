package de.havox_design.aoc2019.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day16Test {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, String expectation, int phases) {
        FlawedFrequencyTransmission objectUnderTest = new FlawedFrequencyTransmission(fileName);
        Assertions.assertEquals(expectation, objectUnderTest.processTask1(phases));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample1.txt", "12345678", 0),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample1.txt", "48226158", 1),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample1.txt", "34040438", 2),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample1.txt", "03415518", 3),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample1.txt", "01029498", 4),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample2.txt", "24176176", 100),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample3.txt", "73745418", 100),
                Arguments.of("de/havox_design/aoc2019/day16/day16part1sample4.txt", "52432133", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, String expectation) {
        Assertions.assertEquals(expectation, FlawedFrequencyTransmission.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day16/day16part2sample1.txt", "84462026"),
                Arguments.of("de/havox_design/aoc2019/day16/day16part2sample2.txt", "78725270"),
                Arguments.of("de/havox_design/aoc2019/day16/day16part2sample3.txt", "53553731")
        );
    }
}
