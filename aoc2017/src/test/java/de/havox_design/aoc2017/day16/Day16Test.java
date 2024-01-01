package de.havox_design.aoc2017.day16;

import de.havox_design.aoc2017.MainClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Day16Test {

    @SuppressWarnings("java:S2699")
    @Test
    void testMainClass() {
        MainClass.main(new String[]{"day16"});
    }

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expected) {
        Assertions.assertEquals(expected, PermutationPromenade.solvePart1(fileName, "abcde"));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day16/day16Part1sample1.txt", "eabcd"),
                Arguments.of("de/havox_design/aoc2017/day16/day16Part1sample2.txt", "eabdc"),
                Arguments.of("de/havox_design/aoc2017/day16/day16Part1sample3.txt", "baedc")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, String expected) {
        Assertions.assertEquals(expected, PermutationPromenade.solvePart2(fileName, "abcde", 2));
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2017/day16/day16Part2sample.txt", "ceadb")
        );
    }
}
