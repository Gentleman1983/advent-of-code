package de.havox_design.aoc2015.day14;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReindeerOlympics implements AoCFunctionality {
    private static final String DATA_PATTERN = "^(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.$";
    
    private final List<Reindeer> input;

    public ReindeerOlympics(String fileName) {
        input = parseData(fileName);
    }

    public static int solvePart1(String fileName) {
        ReindeerOlympics instance = new ReindeerOlympics(fileName);

        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        ReindeerOlympics instance = new ReindeerOlympics(fileName);

        return instance.solvePart2();
    }

    protected static int race(String fileName, int time, boolean newScoringSystem) {
        ReindeerOlympics instance = new ReindeerOlympics(fileName);

        return instance.race(time, newScoringSystem);
    }

    public int solvePart1() {
        return race(2503);
    }

    public int solvePart2() {
        return race(2503, true);
    }

    protected int race(int time, boolean newScoringSystem) {
        return newScoringSystem ? raceForPoints(time) : race(time);
    }

    @SuppressWarnings("squid:S3655")
    protected int race(int time) {
        input
                .stream()
                .parallel()
                .forEach(reindeer -> reindeer.processSeconds(time));

        return input
                .stream()
                .map(Reindeer::getDistance)
                .max(Integer::compareTo)
                .get();
    }

    @SuppressWarnings("squid:S3655")
    protected int raceForPoints(int time) {
        for(int i = 0; i < time; i++) {
            int currentMaxDistance = race(1);

            input
                    .stream()
                    .filter( reindeer -> reindeer.getDistance() == currentMaxDistance )
                    .forEach(Reindeer::awardPoint);
        }

        return input
                .stream()
                .map(Reindeer::getPoints)
                .max(Integer::compareTo)
                .get();
    }

    private List<Reindeer> parseData(String fileName) {
        List<String> data = readData(fileName);
        List<Reindeer> reindeers = new ArrayList<>();

        for(String r : data) {
            reindeers.add(parseReindeer(r));
        }

        return reindeers;
    }

    private Reindeer parseReindeer(String input) {
        Pattern pattern = Pattern.compile(DATA_PATTERN);
        Matcher matcher = pattern.matcher(input);
        
        if(matcher.matches()) {
            return new Reindeer(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
        }
        throw new IllegalArgumentException("Expected the input '" + input + "' to match pattern '" + DATA_PATTERN + "'.");
    }
}
