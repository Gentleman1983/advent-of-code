package de.havox_design.aoc2016.day21.operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwapPosition implements ScramblingOperation {

    public static final Pattern REGEX = Pattern.compile("swap position (\\d+) with position (\\d+)");

    private final int pos1;
    private final int pos2;

    public SwapPosition(Matcher matcher) {
        pos1 = Integer.parseInt(matcher.group(1));
        pos2 = Integer.parseInt(matcher.group(2));
    }

    @Override
    public String scramble(String text) {
        char[] chars = text.toCharArray();
        char tmp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = tmp;
        return new String(chars);
    }
}
