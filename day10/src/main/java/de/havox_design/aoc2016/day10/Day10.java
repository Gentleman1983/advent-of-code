package de.havox_design.aoc2016.day10;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
    private static final Pattern VALUE = Pattern.compile("^value (?<value>\\d+) goes to bot (?<id>\\d+)$");
    private static final Pattern GIVE =
            Pattern.compile("^bot (?<id>\\d+) gives low to (?<lowTarget>bot|output) (?<lowId>\\d+) and high to " +
                    "(?<highTarget>bot|output) (?<highId>\\d+)$");

    private final List<String> input;

    public Day10(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.solvePart1(17, 61);
    }

    public static long solvePart2(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.solvePart2();
    }

    public long solvePart1(int low, int high) {
        return process(input)
                .bots()
                .stream()
                .filter(bot -> bot.low() == low && bot.high() == high)
                .findFirst()
                .map(Bot::id)
                .orElseThrow();
    }

    public long solvePart2() {
        return process(input)
                .filterOutputs(0, 1, 2)
                .values()
                .stream()
                .mapToInt(i -> i)
                .reduce(1, (a, b) -> a * b);
    }

    private Result process(List<String> input) {
        Map<Integer, Bot> bots = new HashMap<>();
        Map<Integer, List<Integer>> outputs = new HashMap<>();
        List<String> copy = new ArrayList<>(input);

        while (!copy.isEmpty()) {
            for (Iterator<String> iterator = copy.iterator(); iterator.hasNext(); ) {
                String instruction = iterator.next();
                Matcher valueMatcher = VALUE.matcher(instruction);
                Matcher giveMatcher = GIVE.matcher(instruction);

                if (valueMatcher.matches()) {
                    int id = Integer.parseInt(valueMatcher.group("id"));
                    int value = Integer.parseInt(valueMatcher.group("value"));
                    bots.merge(id, new Bot(id, value), (a, b) -> a.merge(b.low()));
                    iterator.remove();
                } else if (giveMatcher.matches()) {
                    int id = Integer.parseInt(giveMatcher.group("id"));
                    Target lowTarget = new Target(
                            giveMatcher.group("lowTarget"),
                            Integer.parseInt(giveMatcher.group("lowId"))
                    );
                    Target highTarget = new Target(
                            giveMatcher.group("highTarget"),
                            Integer.parseInt(giveMatcher.group("highId"))
                    );
                    bots.merge(id, new Bot(id, lowTarget, highTarget, null, null), Bot::merge);
                    if (bots.get(id).isInstructionProcessed(bots, outputs)) {
                        iterator.remove();
                    }
                } else {
                    throw new IllegalStateException("Unknown instruction.");
                }
            }
        }

        return new Result(Set.copyOf(bots.values()), Map.copyOf(outputs));
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
