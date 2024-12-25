package de.havox_design.aoc2015.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MedicineForRudolphJavaTest {

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedNumberOfSteps) {
        Assertions.assertEquals(expectedNumberOfSteps, MedicineForRudolphJava.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day19/day19Part2Sample1.txt", 3),
                Arguments.of("de/havox_design/aoc2015/day19/day19Part2Sample2.txt", 6)
        );
    }
}
