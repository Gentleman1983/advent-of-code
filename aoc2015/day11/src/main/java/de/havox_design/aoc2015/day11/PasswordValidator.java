package de.havox_design.aoc2015.day11;

import java.util.Set;
import java.util.stream.Collectors;

public class PasswordValidator {
    public static final char FIRST_VALID_LETTER = 'a';
    public static final int FIRST_VALID_LETTER_VALUE = Character.getNumericValue(FIRST_VALID_LETTER);
    public static final char LAST_VALID_LETTER = 'z';
    public static final int LAST_VALID_LETTER_VALUE = Character.getNumericValue(LAST_VALID_LETTER);
    public static final int LETTER_RANGE = LAST_VALID_LETTER_VALUE - FIRST_VALID_LETTER_VALUE + 1;
    public static final Set<Character> FORBIDDEN_LETTERS = Set.of(
            'l',
            'o',
            'i'
    );
    public static final Set<Byte> FORBIDDEN_LETTERS_VALUES = FORBIDDEN_LETTERS
            .stream()
            .map(c -> (byte) (Character.getNumericValue(c) - FIRST_VALID_LETTER_VALUE))
            .collect(Collectors.toUnmodifiableSet());

    private final byte[] objectUnderTest;

    private PasswordValidator(final byte[] objectUnderTest) {
        super();
        this.objectUnderTest = objectUnderTest;
    }

    public static PasswordValidator getInstance(final byte[] objectUnderTest) {
        return new PasswordValidator(objectUnderTest);
    }

    public boolean validate() {
        return onlyContainsValidLetters()
                && containsSequenceOfThreeIncreasingCharacters()
                && containsTwoDoubleLetters();
    }

    protected boolean containsSequenceOfThreeIncreasingCharacters() {
        for (int i = 0; i < objectUnderTest.length - 2; ++i) {
            int currentCharValue = objectUnderTest[i];
            int nextCharValue = objectUnderTest[i + 1];
            int nextNextCharValue = objectUnderTest[i + 2];
            if (currentCharValue == nextCharValue - 1 && currentCharValue == nextNextCharValue - 2) {
                return true;
            }
        }
        return false;
    }

    protected boolean onlyContainsValidLetters() {
        for (byte b : objectUnderTest) {
            if (FORBIDDEN_LETTERS_VALUES.contains(b)) {
                return false;
            }
        }
        return true;
    }

    protected boolean containsTwoDoubleLetters() {
        int firstStart = calculateStartOfDoubleLetters(0);
        if (firstStart < 0) {
            return false;
        }
        int secondStart = calculateStartOfDoubleLetters(firstStart + 2);
        return secondStart > 0;
    }

    private int calculateStartOfDoubleLetters(int from) {
        int i = from;
        while (i < objectUnderTest.length - 1) {
            if (objectUnderTest[i] == objectUnderTest[i + 1]) {
                return i;
            }
            ++i;
        }
        return -1;
    }
}
