package de.havox_design.aoc2017.day11;

import de.havox_design.aoc2017.MainClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day11Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[]{"day11"});
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expected) {
        Assertions.assertEquals(expected, HexEd.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day11/day11Part1sample1.txt", 3),
                Arguments.of("de/havox_design/aoc2017/day11/day11Part1sample2.txt", 0),
                Arguments.of("de/havox_design/aoc2017/day11/day11Part1sample3.txt", 2),
                Arguments.of("de/havox_design/aoc2017/day11/day11Part1sample4.txt", 3)
        );
    }
}
