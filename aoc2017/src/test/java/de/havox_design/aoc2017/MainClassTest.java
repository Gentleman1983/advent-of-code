package de.havox_design.aoc2017;

import de.havox_design.aoc2017.day09.StreamProcessing;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MainClassTest {

    @ParameterizedTest
    @MethodSource("getDataForMainClass")
    void testMainClass(String arg) {
        MainClass.main(new String[]{arg});
    }

    private static Stream<Arguments> getDataForMainClass() {
        return Stream.of(
                Arguments.of("day01"),
                Arguments.of("day02"),
                Arguments.of("day03"),
                Arguments.of("day04"),
                Arguments.of("day05"),
                Arguments.of("day06"),
                Arguments.of("day07"),
                Arguments.of("day08"),
                Arguments.of("day09"),
                Arguments.of("day10"),
                Arguments.of("day11"),
                Arguments.of("day12"),
                Arguments.of("day13"),
                Arguments.of("day14"),
                Arguments.of("day15"),
                Arguments.of("day16"),
                Arguments.of("day17"),
                Arguments.of("day18"),
                Arguments.of("day19"),
                Arguments.of("day20"),
                Arguments.of("day21"),
                Arguments.of("day22"),
                Arguments.of("day23"),
                Arguments.of("day24"),
                Arguments.of("day25")
        );
    }
}
