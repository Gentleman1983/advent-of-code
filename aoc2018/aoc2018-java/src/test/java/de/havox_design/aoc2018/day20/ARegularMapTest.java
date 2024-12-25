package de.havox_design.aoc2018.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ARegularMapTest {

    @ParameterizedTest
    @MethodSource("getDataForTask1")
    void testTask1(String fileName, int expectation) {
        Assertions.assertEquals(expectation, ARegularMap.processTask1(fileName));
    }

    private static Stream<Arguments> getDataForTask1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2018/day20/day20sample1.txt", 3),
                Arguments.of("de/havox_design/aoc2018/day20/day20sample2.txt", 10),
                Arguments.of("de/havox_design/aoc2018/day20/day20sample3.txt", 18),
                Arguments.of("de/havox_design/aoc2018/day20/day20sample4.txt", 23),
                Arguments.of("de/havox_design/aoc2018/day20/day20sample5.txt", 31)
        );
    }
}
