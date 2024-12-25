package de.havox_design.aoc2018.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SettlersOfTheNorthPoleTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, long expectation, long endMinute) {
        Assertions.assertEquals(expectation, SettlersOfTheNorthPole.processTask1(fileName, endMinute));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 459L,0),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 480L,1),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 550L,2),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 682L,3),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 845L,4),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1056L,5),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1122L,6),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1464L,7),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1674L,8),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1496L,9),
                Arguments.of("de/havox_design/aoc2018/day18/day18sample.txt", 1147L,10)
        );
    }
}
