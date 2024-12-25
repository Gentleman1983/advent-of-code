package de.havox_design.aoc2019.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AmplificationCircuitTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation) {
        Assertions.assertEquals(expectation, AmplificationCircuit.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day07/day07part1sample1.txt", 43210L),
                Arguments.of("de/havox_design/aoc2019/day07/day07part1sample2.txt", 54321L),
                Arguments.of("de/havox_design/aoc2019/day07/day07part1sample3.txt", 65210L)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForTask2")
    void testTask2(String fileName, long expectation) {
        Assertions.assertEquals(expectation, AmplificationCircuit.processTask2(fileName));
    }

    private static Stream<Arguments> getDataForTask2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day07/day07part2sample1.txt", 139629729L),
                Arguments.of("de/havox_design/aoc2019/day07/day07part2sample2.txt", 18216L)
        );
    }
}
