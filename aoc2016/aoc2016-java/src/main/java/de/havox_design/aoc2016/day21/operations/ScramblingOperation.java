package de.havox_design.aoc2016.day21.operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ScramblingOperation {

    static boolean matches(Pattern pattern, String line) {
        return pattern.matcher(line).matches();
    }

    static ScramblingOperation createOperation(String line) {
        Matcher swapPosMatcher = SwapPosition.REGEX.matcher(line);
        if (swapPosMatcher.matches()) {
            return new SwapPosition(swapPosMatcher);
        }
        Matcher swapLetterMatcher = SwapLetter.REGEX.matcher(line);
        if (swapLetterMatcher.matches()) {
            return new SwapLetter(swapLetterMatcher);
        }
        Matcher rotatePositionMatcher = RotatePosition.REGEX.matcher(line);
        if (rotatePositionMatcher.matches()) {
            return new RotatePosition(rotatePositionMatcher);
        }
        Matcher rotateMatcher = Rotate.REGEX.matcher(line);
        if (rotateMatcher.matches()) {
            return new Rotate(rotateMatcher);
        }
        Matcher reverseMatcher = Reverse.REGEX.matcher(line);
        if (reverseMatcher.matches()) {
            return new Reverse(reverseMatcher);
        }
        Matcher moveMatcher = Move.REGEX.matcher(line);
        if (moveMatcher.matches()) {
            return new Move(moveMatcher);
        }

        throw new UnsupportedOperationException(line);
    }

    String scramble(String text);

    default String unscramble(String text) {
        return scramble(text);
    }
}
