package de.havox_design.aoc2019.day09;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.Computer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.stream.Collectors.joining;

public class SensorBoost implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public SensorBoost(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static String processTask1(String fileName) {
        SensorBoost instance = new SensorBoost(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SensorBoost instance = new SensorBoost(fileName);
        return instance.processTask2();
    }

    public String processTask1() {
        return compute(1L);
    }

    public long processTask2() {
        return 0;
    }

    private String compute(long inputNumber ) {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();

        in.add( inputNumber );
        Computer.runComputer( input, in, out, false );

        return out
                .stream()
                .map( String::valueOf )
                .collect( joining( "," ) );
    }
}
