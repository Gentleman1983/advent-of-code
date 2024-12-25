package de.havox_design.aoc2017.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InverseCaptchaTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, InverseCaptcha.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day01/day01Part1sample1.txt", 3L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part1sample2.txt", 4L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part1sample3.txt", 0L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part1sample4.txt", 9L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, InverseCaptcha.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day01/day01Part2sample1.txt", 6L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part2sample2.txt", 0L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part2sample3.txt", 4L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part2sample4.txt", 12L),
                Arguments.of("de/havox_design/aoc2017/day01/day01Part2sample5.txt", 4L)
        );
    }
}
