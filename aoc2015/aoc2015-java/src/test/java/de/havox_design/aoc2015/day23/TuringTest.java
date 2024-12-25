package de.havox_design.aoc2015.day23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TuringTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedValueOfRegisterB) {
        Assertions.assertEquals(expectedValueOfRegisterB, Turing.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day23/day23Part1sample1.txt", 0),
                Arguments.of("de/havox_design/aoc2015/day23/day23Part1sample2.txt", 2)
        );
    }
}
