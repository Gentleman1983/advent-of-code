package de.havox_design.aoc2019.day05;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.Computer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class SunnyWithAChanceOfAsteroids implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public SunnyWithAChanceOfAsteroids(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        SunnyWithAChanceOfAsteroids instance = new SunnyWithAChanceOfAsteroids(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SunnyWithAChanceOfAsteroids instance = new SunnyWithAChanceOfAsteroids(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return process(1L);
    }

    public long processTask2() {
        return 0;
    }

    private Long process(long inputNumber ) {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();

        in.add( inputNumber );

        Computer.runComputer( input, in, out, false );

        return out.removeLast();
    }

}
