package de.havox_design.aoc2019.day19;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class TractorBeam implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public TractorBeam(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        TractorBeam instance = new TractorBeam(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        TractorBeam instance = new TractorBeam(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        int size = 50;
        long[][] matrix = new long[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                matrix[y][x] = getNumber(input, in, out, x, y);
            }
        }

        return Arrays
                .stream(matrix)
                .flatMapToLong(Arrays::stream)
                .filter(e -> e == 1L)
                .count();
    }

    public long processTask2() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        int size = 100;
        int y = size;
        int x = 1;

        while (true) {
            long number;

            do {
                number = getNumber(input, in, out, x, y);

                if (number == 0) {
                    x++;
                }
            } while (number == 0);

            if (getNumber(input, in, out, x + (size - 1), y - (size - 1)) == 1L) {
                y -= (size - 1);

                return x * 10000L + y;
            }

            y++;
        }
    }

    @SuppressWarnings("squid:S2142")
    private long getNumber(
            List<Long> program,
            BlockingQueue<Long> in,
            BlockingDeque<Long> out,
            int x,
            int y
    ) {
        in.add((long) x);
        in.add((long) y);

        try {
            IntComputer.runComputer(program, in, out, false);
            return out.take();
        } catch (InterruptedException e) {
            throw new AdventOfCodeException(e);
        }
    }
}
