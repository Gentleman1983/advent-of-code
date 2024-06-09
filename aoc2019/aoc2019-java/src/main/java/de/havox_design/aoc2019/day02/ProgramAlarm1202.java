package de.havox_design.aoc2019.day02;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramAlarm1202 implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public ProgramAlarm1202(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
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
        List<Long> program = new ArrayList<>(input);
        long noun = 12L;
        long verb = 2L;

        return compute(program, noun, verb);
    }

    public long processTask2() {
        List<Long> program = new ArrayList<>(input);
        long expectedOutput = 19690720L;
        long minValue = 0L;
        long maxValue = 99L;
        long nounMultiplicator = 100L;

        for (long noun = minValue; noun <= maxValue; noun++) {
            for (long verb = minValue; verb <= maxValue; verb++) {
                if (compute(program, noun, verb).equals(expectedOutput)) {
                    return nounMultiplicator * noun + verb;
                }
            }
        }

        throw new IllegalStateException(
                String
                        .format(
                                "This should never happen. Did not find any solution to produce output '%s' using " +
                                        "noun and verb values from %s to %s.",
                                expectedOutput,
                                minValue,
                                maxValue
                        )
        );
    }

    private Long compute(List<Long> program, long noun, long verb) {
        if (program.size() > 10) {
            program.set(1, noun);
            program.set(2, verb);
        }

        IntComputer computer = new IntComputer(program);

        computer.run();

        return computer
                .getMemory()
                .get(0L);
    }
}
