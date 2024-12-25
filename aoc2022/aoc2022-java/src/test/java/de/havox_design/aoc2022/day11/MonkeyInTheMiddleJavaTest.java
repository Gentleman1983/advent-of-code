package de.havox_design.aoc2022.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MonkeyInTheMiddleJavaTest {

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expected, int runs) {
        Assertions.assertEquals(expected, MonkeyInTheMiddleJava.solvePart2(fileName, runs));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 24L, 1),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 99L * 103L, 20),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 5204L * 5192L, 1000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 10419L * 10391L, 2000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 15638L * 15593L, 3000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 20858L * 20797L, 4000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 26075L * 26000L, 5000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 31294L * 31204L, 6000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 36508L * 36400L, 7000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 41728L * 41606L, 8000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 46945L * 46807L, 9000),
                Arguments.of("de/havox_design/aoc2022/day11/day11part2sample.txt", 52166L * 52013L, 10000)
        );
    }
}
