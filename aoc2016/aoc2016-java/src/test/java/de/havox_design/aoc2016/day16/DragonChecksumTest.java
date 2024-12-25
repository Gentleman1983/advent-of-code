package de.havox_design.aoc2016.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DragonChecksumTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expected) {
        Assertions.assertEquals(expected, DragonChecksum.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day16/day16Sample.txt", "11010")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, String expected) {
        Assertions.assertEquals(expected, DragonChecksum.solvePart2(fileName));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2016/day16/day16Sample.txt", "10111")
        );
    }
}
