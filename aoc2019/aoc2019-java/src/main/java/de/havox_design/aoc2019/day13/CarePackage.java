package de.havox_design.aoc2019.day13;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.*;

public class CarePackage implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final char EMPTY = ' ';
    private static final char WALL = '#';
    private static final char BLOCK = '=';
    private static final char PADDLE = '|';
    private static final char BALL = 'o';
    private static final Map<Long, Character> TILES = Map.of( 0L, EMPTY, 1L, WALL, 2L, BLOCK,
            3L, PADDLE, 4L, BALL );

    private final List<Long> input;

    public CarePackage(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        CarePackage instance = new CarePackage(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        CarePackage instance = new CarePackage(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return compute();
    }

    public long processTask2() {
        return 0;
    }

    @SuppressWarnings("squid:S2142")
    private long compute() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        Future<?> future = IntComputer.runComputer(input, in, out, true);

        Map<Pair<Long, Long>, Character> grid = new HashMap<>();
        long paddlePosition = 0L;

        try {
            do {
                Long x = readOutput(out, future);

                if (x == null) {
                    break;
                }

                Long y = out.take();
                Long tile = out.take();
                Character symbol = TILES.get(tile);

                grid.put(Pair.of(y, x), symbol);
                paddlePosition = (symbol == PADDLE) ? x : paddlePosition;
            } while (true);
        } catch (InterruptedException e) {
            throw new AdventOfCodeException(e);
        }

        return grid.values().stream().filter(c -> c == BLOCK).count();
    }

    public Long readOutput(BlockingDeque<Long> output, Future<?> writer)
            throws InterruptedException {
        Long x;
        do {
            x = output.poll( 50, TimeUnit.MILLISECONDS );
        } while ( x == null && !writer.isDone() );
        return x;
    }
}
