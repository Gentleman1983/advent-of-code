package de.havox_design.aoc2016.day21;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rotate implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("rotate (right|left) (\\d+) steps?");

    private final int delta;

    private boolean left;

    public Rotate(Matcher matcher) {
        left = matcher.group(1).equals("left");
        delta = Integer.valueOf(matcher.group(2));
    }

    @Override
    public String scramble(String text) {
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            if (left) {
                chars[(text.length() + i - delta) % text.length()] = text.charAt(i);
            } else {
                chars[(i + delta) % text.length()] = text.charAt(i);
            }
        }
        return new String(chars);
    }

    @Override
    public String unscramble(String text) {
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            if (left) {
                chars[(i + delta) % text.length()] = text.charAt(i);
            } else {
                chars[(text.length() + i - delta) % text.length()] = text.charAt(i);
            }
        }
        return new String(chars);
    }
}
