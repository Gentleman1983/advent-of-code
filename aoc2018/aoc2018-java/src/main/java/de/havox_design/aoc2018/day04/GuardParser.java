package de.havox_design.aoc2018.day04;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuardParser {
    private static final String ACTION_FORMAT = "\\[(.+)] (.+)";
    private static final String GUARD_FORMAT = "Guard #(\\d+) begins shift";
    private static final String WAKE_FORMAT = "wakes up";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final Pattern ACTION_PATTERN = Pattern.compile(ACTION_FORMAT);
    private static final Pattern GUARD_PATTERN = Pattern.compile(GUARD_FORMAT);


    @SuppressWarnings("squid:S3864")
    public List<Guard> parse(Collection<String> input) {
        List<Pair<LocalDateTime, String>> actions = input
                .stream()
                .map(ACTION_PATTERN::matcher)
                .peek(Matcher::matches)
                .map(Matcher::toMatchResult)
                .map(m ->
                        Pair
                                .of(
                                        LocalDateTime.parse(m.group(1), DATE_TIME_FORMATTER),
                                        m.group(2)
                                )
                )
                .sorted(Comparator.comparing(Pair::getLeft))
                .toList();

        List<List<Pair<LocalDateTime, String>>> chunkedByShift = chunkByShift(actions);

        return chunkedByShift
                .stream()
                .map(this::parseShiftChunk)
                .collect(Collectors.groupingBy(Pair::getLeft, Collectors.mapping(Pair::getRight, Collectors.toList())))
                .entrySet()
                .stream()
                .map(e ->
                        new Guard(
                                e.getKey(),
                                e.getValue()
                        )
                )
                .toList();
    }

    private List<List<Pair<LocalDateTime, String>>> chunkByShift(List<Pair<LocalDateTime, String>> actions) {
        List<Integer> shiftInits = IntStream
                .range(0, actions.size())
                .filter(i ->
                        GUARD_PATTERN
                                .matcher(actions.get(i).getRight())
                                .matches()
                )
                .boxed()
                .toList();

        List<Integer> shiftEnds = IntStream
                .concat(
                        IntStream
                                .range(1, shiftInits.size())
                                .map(shiftInits::get),
                        IntStream
                                .of(actions.size())
                )
                .boxed()
                .toList();

        return IntStream
                .range(0, shiftInits.size())
                .mapToObj(i -> actions.subList(shiftInits.get(i), shiftEnds.get(i)))
                .toList();
    }

    private Pair<Integer, Shift> parseShiftChunk(List<Pair<LocalDateTime, String>> actions) {
        Matcher guardMatcher = GUARD_PATTERN
                .matcher(
                        actions
                                .getFirst()
                                .getRight()
                );

        if (guardMatcher.matches()) {
            int guardId = Integer.parseInt(guardMatcher.group(1));
            boolean[] awake = new boolean[60];
            Arrays.fill(awake, true);

            actions
                    .subList(1, actions.size())
                    .forEach(p -> {
                                int min = p.getLeft().getMinute();
                                boolean wakeFormat = p.getRight().matches(WAKE_FORMAT);
                                IntStream.range(min, 60).forEach(i -> awake[i] = wakeFormat);
                            }
                    );

            return Pair.of(guardId, new Shift(Arrays.asList(ArrayUtils.toObject(awake))));
        }

        throw new IllegalStateException("This should never be reached...");
    }
}
