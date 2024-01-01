package de.havox_design.aoc2017.day25;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    private static final Pattern EXTRACTOR =
            Pattern.compile("^.* ([0-9A-Z]+|left|right)(?: steps)?[.:]$");

    private Helper() {}

    protected static Map<Character, State> getStateMap(Iterator<String> iterator) {
        Map<Character, State> map = new HashMap<>();

        while (iterator.hasNext()) {
            iterator.next();
            map.put(
                    getCharacter(iterator.next()),
                    new State(
                            Step.create(iterator),
                            Step.create(iterator)
                    )
            );
        }
        return map;
    }

    protected static char getCharacter(String input) {
        return getValue(input).charAt(0);
    }

    protected static int getNumber(String input) {
        return Integer.parseInt(getValue(input));
    }

    protected static String getValue(String input) {
        Matcher matcher = EXTRACTOR.matcher(input);

        if (matcher.matches()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("Expected value");
    }
}
