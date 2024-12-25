package de.havox_design.aoc2016.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SecurityThroughObscurityTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected) {
        Assertions.assertEquals(expected, SecurityThroughObscurity.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day04/day04Part1sample1.txt", 123L),
                Arguments.of("de/havox_design/aoc2016/day04/day04Part1sample2.txt", 987L),
                Arguments.of("de/havox_design/aoc2016/day04/day04Part1sample3.txt", 404L),
                Arguments.of("de/havox_design/aoc2016/day04/day04Part1sample4.txt", 0L),
                Arguments.of("de/havox_design/aoc2016/day04/day04Part1sample5.txt", 1514L)
        );
    }
}
