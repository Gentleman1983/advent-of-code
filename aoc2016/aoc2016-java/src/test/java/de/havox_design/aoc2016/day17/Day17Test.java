package de.havox_design.aoc2016.day17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day17Test {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expected) {
        Assertions.assertEquals(expected, TwoStepsForward.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample1.txt", "DDRRRD"),
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample2.txt", "DDUDRLRRUDRD"),
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample3.txt", "DRURDRUDDLLDLUURRDULRLDUUDDDRR")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected) {
        Assertions.assertEquals(expected, TwoStepsForward.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample1.txt", 370L),
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample2.txt", 492L),
                Arguments.of("de/havox_design/aoc2016/day17/day17Sample3.txt", 830L)
        );
    }
}
