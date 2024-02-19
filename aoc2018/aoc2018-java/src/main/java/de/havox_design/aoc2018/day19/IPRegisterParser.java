package de.havox_design.aoc2018.day19;

public class IPRegisterParser {
    private static final String IP = "#ip ";

    public Integer parse(final String toParse) {
        if (toParse.startsWith(IP)) {
            return Integer.parseInt(toParse.substring(IP.length()).trim());
        }

        throw new IllegalArgumentException("Could not parse " + toParse);
    }
}
