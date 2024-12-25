package de.havox_design.aoc2022.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ProboscideaVolcaniumJavaTest {

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, int expected) {
        Assertions.assertEquals(expected, ProboscideaVolcaniumJava.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2022/day16/day16part2sample.txt", 1707)
        );
    }
}
