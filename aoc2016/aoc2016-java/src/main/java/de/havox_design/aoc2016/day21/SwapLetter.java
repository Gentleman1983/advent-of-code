package de.havox_design.aoc2016.day21;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwapLetter implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("swap letter (\\w) with letter (\\w)");

    private final char letter1;

    private final char letter2;

    public SwapLetter(Matcher matcher) {
        letter1 = matcher.group(1).charAt(0);
        letter2 = matcher.group(2).charAt(0);
    }

    @Override
    public String scramble(String text) {
        return text.replace(letter1, 'X').replace(letter2, letter1).replace('X', letter2);
    }
}
