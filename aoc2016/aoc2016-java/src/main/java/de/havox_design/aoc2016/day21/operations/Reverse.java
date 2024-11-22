package de.havox_design.aoc2016.day21.operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reverse implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("reverse positions (\\d+) through (\\d+)");

    private final int pos1;
    private final int pos2;

    public Reverse(Matcher matcher) {
        pos1 = Integer.parseInt(matcher.group(1));
        pos2 = Integer.parseInt(matcher.group(2));
    }

    @Override
    public String scramble(String text) {
        char[] chars = text.toCharArray();
        for (int i = Math.min(pos1, pos2); i <= Math.max(pos1, pos2); i++) {
            chars[i] = text.charAt(pos2 + pos1 - i);
        }
        return new String(chars);
    }
}
