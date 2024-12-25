package de.havox_design.aoc2017.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SporificaVirusTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, long expected, int numberOfIterations) {
        Assertions.assertEquals(expected, SporificaVirus.solvePart1(fileName, numberOfIterations));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day22/day22Sample.txt", 5L, 7),
                Arguments.of("de/havox_design/aoc2017/day22/day22Sample.txt", 41L, 70),
                Arguments.of("de/havox_design/aoc2017/day22/day22Sample.txt", 5587L, 10000)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, long expected, int numberOfIterations) {
        Assertions.assertEquals(expected, SporificaVirus.solvePart2(fileName, numberOfIterations));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day22/day22Sample.txt", 26L, 100),
                Arguments.of("de/havox_design/aoc2017/day22/day22Sample.txt", 2511944L, 10000000)
        );
    }
}
