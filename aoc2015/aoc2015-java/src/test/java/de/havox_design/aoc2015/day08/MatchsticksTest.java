package de.havox_design.aoc2015.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MatchsticksTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, int expectedResult, int expectedNumberOfCharactersOfCode, int expectedNumberOfCharactersInMemory) {
        Matchsticks objectUnderTest = new Matchsticks(fileName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        expectedNumberOfCharactersOfCode,
                        objectUnderTest.calculateCharactersOfCode(),
                        "Expected to be " + expectedNumberOfCharactersOfCode + " characters of code, but was " +
                                objectUnderTest.calculateCharactersOfCode()
                ),
                () -> Assertions.assertEquals(
                        expectedNumberOfCharactersInMemory,
                        objectUnderTest.calculateCharactersInMemory(),
                        "Expected to be " + expectedNumberOfCharactersInMemory + " characters in memory, but was " +
                                objectUnderTest.calculateCharactersInMemory()
                ),
                () -> Assertions.assertEquals(
                        expectedResult,
                        objectUnderTest.solvePart1(),
                        "Expected difference " + expectedResult + ", but was " + objectUnderTest.solvePart1()
                )
        );
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample1.txt", 2, 2, 0),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample2.txt", 2, 5, 3),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample3.txt", 3, 10, 7),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample4.txt", 5, 6, 1),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample5.txt", 12, 23, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForPart2")
    void testPart2(String fileName, int expectedResult, int expectedNumberOfCharactersOfCode, int expectedNumberOfEncodedCharacters) {
        Matchsticks objectUnderTest = new Matchsticks(fileName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        expectedNumberOfCharactersOfCode,
                        objectUnderTest.calculateCharactersOfCode(),
                        "Expected to be " + expectedNumberOfCharactersOfCode + " characters of code, but was " +
                                objectUnderTest.calculateCharactersOfCode()
                ),
                () -> Assertions.assertEquals(
                        expectedNumberOfEncodedCharacters,
                        objectUnderTest.calculateEncodedCharacters(),
                        "Expected to be " + expectedNumberOfEncodedCharacters + " encoded characters, but was " +
                                objectUnderTest.calculateEncodedCharacters()
                ),
                () -> Assertions.assertEquals(
                        expectedResult,
                        objectUnderTest.solvePart2(),
                        "Expected difference " + expectedResult + ", but was " + objectUnderTest.solvePart2()
                )
        );
    }

    private static Stream<Arguments> getDataForPart2() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample1.txt", 4, 2, 6),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample2.txt", 4, 5, 9),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample3.txt", 6, 10, 16),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample4.txt", 5, 6, 11),
                Arguments.of("de/havox_design/aoc2015/day08/day08Sample5.txt", 19, 23, 42)
        );
    }
}
