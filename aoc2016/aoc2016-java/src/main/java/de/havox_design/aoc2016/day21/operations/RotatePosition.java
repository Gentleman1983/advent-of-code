package de.havox_design.aoc2016.day21.operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RotatePosition implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("rotate based on position of letter (\\w)");

    private final char letter;

    public RotatePosition(Matcher matcher) {
        letter = matcher.group(1).charAt(0);
    }

    @Override
    public String scramble(String text) {
        int delta = delta(text.indexOf(letter));
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            chars[(i + delta) % text.length()] = text.charAt(i);
        }
        return new String(chars);
    }

    @Override
    public String unscramble(String text) {
        int idx = text.indexOf(letter);
        int delta = delta(IntStream.rangeClosed(0, 7).filter(x -> (x + delta(x)) % 8 == idx).findFirst().getAsInt());
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            chars[(i + text.length() - delta) % text.length()] = text.charAt(i);
        }
        return new String(chars);
    }

    public static int delta(int index) {
        return (1 + index + (index >= 4 ? 1 : 0)) % 8;
    }
}
