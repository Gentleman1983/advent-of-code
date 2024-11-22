package de.havox_design.aoc2022.day17;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PyroclasticFlowJava implements AoCFunctionality {
    final String input;

    public PyroclasticFlowJava(String fileName) {
        input = readString(fileName);
    }

    public static long solvePart2(String fileName) {
        PyroclasticFlowJava instance = new PyroclasticFlowJava(fileName);

        return instance.solvePart2();
    }

    public long solvePart2() {
        Tetris tetris = new Tetris(input);

        List<Integer> dy = new ArrayList<>();
        int cycleLength;

        while ((cycleLength = findCycleLength(dy)) == 0) {
            int y1 = tetris.grid.size();
            tetris.tick();
            int y2 = tetris.grid.size();
            dy.add(y2 - y1);
        }

        long rocks = 1_000_000_000_000L;
        long count = dy.size() - 2L * cycleLength;
        long height = dy.subList(0, (int) count).stream().mapToInt(Integer::intValue).sum();

        rocks -= count;
        dy = dy.subList((int) count, (int) count + cycleLength);
        count = rocks / dy.size();
        height += count * dy.stream().mapToInt(Integer::intValue).sum();
        rocks -= count * dy.size();
        height += dy.subList(0, (int) rocks).stream().mapToInt(Integer::intValue).sum();

        return height;
    }

    private int findCycleLength(List<Integer> sequence) {
        for (int length = input.trim().length(); length < sequence.size() / 2; length++) {
            boolean matches = true;

            for (int i = 0; i < length; i++) {
                if (!Objects.equals(sequence.get(sequence.size() - 2 * length + i), sequence.get(sequence.size() - length + i))) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                return length;
            }
        }

        return 0;
    }
}
