package de.havox_design.aoc2015.day17;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class NotEnoughEggnod implements AoCFunctionality {
    private final List<Integer> input;

    public NotEnoughEggnod(String fileName) {
        input = readData(fileName)
                .stream()
                .map(Integer::parseInt)
                .toList();
    }

    public static int solvePart1(String fileName) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);

        return instance.solvePart1();
    }

    protected static int countCombinations(String fileName, int amountOfEggnod) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);

        return instance.countWays(instance.input, 0, amountOfEggnod, 0);
    }

    public static int solvePart2(String fileName) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);

        return instance.solvePart2();
    }

    protected static int countLimitedCombinations(String fileName, int amountOfEggnod) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);
        int minContainerRequirement = instance.findMinimumContainerCount(instance.input, 0, amountOfEggnod, 0);

        return instance
                .countWaysWithLimitedContainers(instance.input, 0, amountOfEggnod, 0, minContainerRequirement);
    }

    public int solvePart1() {
        return countWays(input, 0, 150, 0);
    }

    public int solvePart2() {
        int minContainerRequirement = findMinimumContainerCount(input, 0, 150, 0);

        return countWaysWithLimitedContainers(input, 0, 150, 0, minContainerRequirement);
    }

    private int countWays(List<Integer> containers, int startIndex, int target, int countSoFar) {
        if (target == 0) {
            return 1;
        } else if (target < 0) {
            return 0;
        } else {
            int count = countSoFar;

            for (int i = startIndex; i < containers.size(); ++i) {
                count += countWays(containers, i + 1, target - containers.get(i), countSoFar);
            }

            return count;
        }
    }

    private int findMinimumContainerCount(List<Integer> containers, int startIndex, int target, int containerCount) {
        if (target == 0) {
            return containerCount;
        } else if (target < 0) {
            return Integer.MAX_VALUE;
        } else {
            int min = Integer.MAX_VALUE;

            for (int i = startIndex; i < containers.size(); ++i) {
                min = Math
                        .min(
                                min,
                                findMinimumContainerCount(containers, i + 1, target - containers.get(i), containerCount + 1)
                        );
            }

            return min;
        }
    }

    private int countWaysWithLimitedContainers(List<Integer> containers, int startIndex, int target, int countSoFar, int usableContainers) {
        if (target == 0 && usableContainers >= 0) {
            return 1;
        } else if (target < 0 || usableContainers < 0) {
            return 0;
        } else {
            int count = countSoFar;

            for (int i = startIndex; i < containers.size(); ++i) {
                count += countWaysWithLimitedContainers(
                        containers,
                        i + 1,
                        target - containers.get(i),
                        countSoFar,
                        usableContainers - 1
                );
            }

            return count;
        }
    }
}
