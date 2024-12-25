package de.havox_design.aoc2022.day17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PyroclasticFlowJavaTest {

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expected) {
        Assertions.assertEquals(expected, PyroclasticFlowJava.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2022/day17/day17part2sample.txt", 1514285714288L)
        );
    }
}
