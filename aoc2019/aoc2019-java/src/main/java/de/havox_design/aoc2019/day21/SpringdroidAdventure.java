package de.havox_design.aoc2019.day21;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class SpringdroidAdventure implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public SpringdroidAdventure(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        SpringdroidAdventure instance = new SpringdroidAdventure(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SpringdroidAdventure instance = new SpringdroidAdventure(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return caculateDamage(false);
    }

    public long processTask2() {
        return caculateDamage(true);
    }

    private long caculateDamage(boolean isExtendedSensorModeActive) {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        initializeCommands(in, isExtendedSensorModeActive);

        IntComputer.runComputer(input, in, out, false);

        return out.removeLast();
    }

    private void initializeCommands(BlockingQueue<Long> in, boolean isExtendedSensorModeActive) {
        String instructions = isExtendedSensorModeActive ?
                """
                        OR A J
                        AND B J
                        AND C J
                        NOT J J
                        AND D J
                        OR E T
                        OR H T
                        AND T J
                        RUN
                        """
                :
                """
                        OR A J
                        AND C J
                        NOT J J
                        AND D J
                        WALK
                        """;

        instructions
                .chars()
                .boxed()
                .forEach(c -> in.add(c.longValue()));
    }
}
