package de.havox_design.aoc2016.day21;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("move position (\\d+) to position (\\d+)");

    private final int pos1;

    private final int pos2;

    public Move(Matcher matcher) {
        pos1 = Integer.valueOf(matcher.group(1));
        pos2 = Integer.valueOf(matcher.group(2));
    }

    @Override
    public String scramble(String text) {
        char ch = text.charAt(pos1);
        StringBuilder sb = new StringBuilder(text);
        sb.delete(pos1, pos1 + 1);
        sb.insert(pos2, ch);
        return sb.toString();
    }

    @Override
    public String unscramble(String text) {
        char ch = text.charAt(pos2);
        StringBuilder sb = new StringBuilder(text);
        sb.delete(pos2, pos2 + 1);
        sb.insert(pos1, ch);
        return sb.toString();
    }
}
