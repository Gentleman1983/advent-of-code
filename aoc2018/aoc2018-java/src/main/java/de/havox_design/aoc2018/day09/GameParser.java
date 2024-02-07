package de.havox_design.aoc2018.day09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameParser {
    private static final String GAME_FORMAT = "(\\d+) players; last marble is worth (\\d+) points";
    private static final Pattern GAME_PATTERN = Pattern.compile(GAME_FORMAT);
    private static final int INDEX_NUMBER_OF_PLAYERS = 1;
    private static final int INDEX_LAST_MARBLE = 2;

    public GameResult parse(String s) {
        Matcher m = GAME_PATTERN.matcher(s);

        if (!m.matches()) {
            throw new IllegalArgumentException();
        }

        return new GameResult(
                Integer.parseInt(m.group(INDEX_NUMBER_OF_PLAYERS)),
                Integer.parseInt(m.group(INDEX_LAST_MARBLE))
        );
    }
}
