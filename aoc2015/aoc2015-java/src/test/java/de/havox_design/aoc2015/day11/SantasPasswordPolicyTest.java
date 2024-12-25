package de.havox_design.aoc2015.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static de.havox_design.aoc2015.day11.PasswordValidator.FIRST_VALID_LETTER_VALUE;

class SantasPasswordPolicyTest {

    @ParameterizedTest
    @MethodSource("getDataForPart1")
    void testPart1(String fileName, String expectedNextPassword) {
        Assertions.assertEquals(expectedNextPassword, SantasPasswordPolicy.solvePart1(fileName));
    }

    private static Stream<Arguments> getDataForPart1() {
        return Stream.of(
                Arguments.of("de/havox_design/aoc2015/day11/day11Part1sample1.txt", "abcdffaa"),
                Arguments.of("de/havox_design/aoc2015/day11/day11Part1sample2.txt", "ghjaabcc")
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForValidationTest")
    void testValidation(
            String objectUnderTest,
            boolean expectedToContainStraightOfLetters,
            boolean expectedToNotContainForbiddenCharacters,
            boolean expectedToContainTwoDoubleLetters,
            boolean expectedToBeValid
    ) {
        PasswordValidator validator = PasswordValidator.getInstance(encodeString(objectUnderTest));

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        expectedToContainStraightOfLetters,
                        validator.containsSequenceOfThreeIncreasingCharacters(),
                        () -> "Expectation of straight of letters was " + expectedToContainStraightOfLetters + ", but was " + validator.containsSequenceOfThreeIncreasingCharacters()
                ),
                () -> Assertions.assertEquals(
                        expectedToNotContainForbiddenCharacters,
                        validator.onlyContainsValidLetters(),
                        () -> "Expectation to contain only valid letters was " + expectedToNotContainForbiddenCharacters + ", but was " + validator.onlyContainsValidLetters()
                ),
                () -> Assertions.assertEquals(
                        expectedToContainTwoDoubleLetters,
                        validator.containsTwoDoubleLetters(),
                        () -> "Expectation of at least two double letters was " + expectedToContainTwoDoubleLetters + ", but was " + validator.containsTwoDoubleLetters()
                ),
                () -> Assertions.assertEquals(
                        expectedToBeValid,
                        validator.validate(),
                        () -> "Expectation to be valid password was " + expectedToBeValid + ", but was " + validator.validate()
                )
        );
    }

    private static Stream<Arguments> getDataForValidationTest() {
        return Stream.of(
                Arguments.of("hijklmmn", true, false, false, false),
                Arguments.of("abbceffg", false, true, true, false),
                Arguments.of("abbcegjk", false, true, false, false),
                Arguments.of("abcdffaa", true, true, true, true),
                Arguments.of("ghjaabcc", true, true, true, true)
        );
    }

    private byte[] encodeString(String string) {
        byte[] encodedString = new byte[string.length()];
        for (int i = 0; i < encodedString.length; ++i) {
            encodedString[i] = (byte) (Character.getNumericValue(string.charAt(i)) - FIRST_VALID_LETTER_VALUE);
        }
        return encodedString;
    }
}
