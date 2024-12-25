package de.havox_design.aoc2019.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SensorBoostTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, String expectation) {
        Assertions.assertEquals(expectation, SensorBoost.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2019/day09/day09part1sample1.txt", "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"),
                Arguments.of("de/havox_design/aoc2019/day09/day09part1sample2.txt", "1219070632396864"),
                Arguments.of("de/havox_design/aoc2019/day09/day09part1sample3.txt", "1125899906842624")
        );
    }
}
