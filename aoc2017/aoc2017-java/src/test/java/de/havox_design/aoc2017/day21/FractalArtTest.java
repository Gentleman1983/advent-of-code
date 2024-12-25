package de.havox_design.aoc2017.day21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FractalArtTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int numberOfIterations) {
        Assertions.assertEquals(expected, FractalArt.solvePart1(fileName, numberOfIterations));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day21/day21Sample.txt", 12L, 2)
        );
    }
}
