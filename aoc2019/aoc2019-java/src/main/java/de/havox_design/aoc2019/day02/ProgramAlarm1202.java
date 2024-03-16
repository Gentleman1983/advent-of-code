package de.havox_design.aoc2019.day02;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.Computer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramAlarm1202 implements AoCFunctionality {
    private final List<Long> input;

    public ProgramAlarm1202(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(","))
                .map(value -> Long.parseLong(value.trim()))
                .collect(Collectors.toList());
    }

    public static long processTask1(String fileName) {
        ProgramAlarm1202 instance = new ProgramAlarm1202(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ProgramAlarm1202 instance = new ProgramAlarm1202(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        List<Long> program = input;

        return compute( program, 12, 2 );
    }

    public long processTask2() {
        return 0;
    }

    private Long compute(List<Long> program, long noun, long verb ) {
        if ( program.size() > 10 ) {
            program.set( 1, noun );
            program.set( 2, verb );
        }

        final Computer computer = new Computer( program );
        computer.run();
        return computer.memory.get( 0L );
    }
}
