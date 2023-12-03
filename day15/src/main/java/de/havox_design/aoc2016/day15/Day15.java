package de.havox_design.aoc2016.day15;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    private static final Pattern DISC = Pattern.compile(
            "^Disc #(?<discNumber>\\d+) has (?<positions>\\d+) positions; at time=(?<startTime>\\d+), it is at " +
                    "position (?<startPosition>\\d+).$"
    );

    private final List<String> input;
    private final List<Disc> discs;

    public Day15(String fileName) {
        input = readData(fileName);
        discs = calculateDiscs();
    }

    public static long solvePart1(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return solve();
    }

    public long solvePart2() {
        discs.add(new Disc(11, 0));

        return solve();
    }

    private long solve() {
        long startTime = 0;
        long step = 1;

        for (int i = 0; i < discs.size(); i++) {
            Disc disc = discs.get(i);
            while (((disc.startPos() + startTime + i + 1) % disc.posCount()) != 0) {
                startTime += step;
            }
            step *= disc.posCount();
        }

        return startTime;
    }

    private List<Disc> calculateDiscs() {
        List<Disc> result = new ArrayList<>();

        for (String line : input) {
            result.add(calculateDisc(line));
        }

        return result;
    }

    private Disc calculateDisc(String inputLine) {
        Matcher discMatcher = DISC.matcher(inputLine);

        if(discMatcher.matches()) {
            int discPositions = Integer.parseInt(discMatcher.group("positions"));
            int discStartPosition = Integer.parseInt(discMatcher.group("startPosition"));


            return new Disc(discPositions, discStartPosition);
        }
        throw new IllegalArgumentException("Invalid input '" + inputLine + "'.");
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
