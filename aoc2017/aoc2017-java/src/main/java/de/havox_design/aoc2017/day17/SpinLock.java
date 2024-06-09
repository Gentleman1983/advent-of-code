package de.havox_design.aoc2017.day17;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpinLock implements AoCFunctionality {
    private final int input;

    public SpinLock(String fileName) {
        input = Integer.parseInt(readData(fileName).getFirst());
    }

    public static long solvePart1(String fileName) {
        SpinLock instance = new SpinLock(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        SpinLock instance = new SpinLock(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        return calculateValueAfter2017Iterations(input);
    }

    public long solvePart2() {return calculateValueAfter0After50MillionIterations(input);
    }

    private int calculateValueAfter2017Iterations(int initialSteps) {
        int[] values = new int[]{0};

        for (int i = 1, index = 1; ;i++, index = (index + initialSteps) % i + 1) {
            values = Stream
                    .of(
                            Arrays.stream(values, 0, index),
                            IntStream.of(i),
                            Arrays.stream(values, index, i)
                    )
                    .flatMapToInt(Function.identity())
                    .toArray();

            if (i == 2017) {
                return values[index + 1];
            }
        }
    }

    private int calculateValueAfter0After50MillionIterations(int initialSteps) {
        int result = 0;

        for (int i = 1, index = 1; i <= 50000000; i++, index = (index + initialSteps) % i + 1) {
            if (index == 1) {
                result = i;
            }
        }

        return result;
    }
}
