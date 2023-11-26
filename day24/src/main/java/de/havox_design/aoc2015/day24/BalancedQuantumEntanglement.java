package de.havox_design.aoc2015.day24;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.ArrayList;
import java.util.List;

public class BalancedQuantumEntanglement {
    private final List<String> input;

    public BalancedQuantumEntanglement(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        BalancedQuantumEntanglement instance = new BalancedQuantumEntanglement(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        BalancedQuantumEntanglement instance = new BalancedQuantumEntanglement(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return processDay24(3);
    }

    public long solvePart2() {
        return processDay24(4);
    }

    private long processDay24(int numberOfGroups) {
        List<Integer> weights = input
                .stream()
                .map(Integer::valueOf)
                .toList();
        int weight = weights
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return findSolution(weights, weight / numberOfGroups, numberOfGroups == 4);
    }

    private long findSolution(final List<Integer> weights, final int groupWeight, final boolean isPart2) {
        final LengthSortedResult combinations = calcCombinations(weights, groupWeight);
        for (Integer[] firstCombination : combinations) {
            List<Integer> remaining = difference(weights, firstCombination);
            final LengthSortedResult combinationsForSecondGroup = calcCombinations(remaining, groupWeight);
            if (isPart2) {
                if (combinationsForSecondGroup.size() > 0) {
                    return quantumEntanglement(firstCombination);
                }
            } else if (combinationsForSecondGroup.size() > 0) {
                for (final Integer[] secondCombination : combinationsForSecondGroup) {
                    final LengthSortedResult combinationsForThirdGroup = calcCombinations(difference(remaining, secondCombination), groupWeight);
                    if (combinationsForThirdGroup.size() > 0) {
                        return quantumEntanglement(firstCombination);
                    }
                }
            }
        }
        return -1;
    }
    private LengthSortedResult calcCombinations(List<Integer> weights, int groupWeight) {
        LengthSortedResult res = new LengthSortedResult(weights.size() + 1);
        calcCombinations(weights, groupWeight, new ArrayList<>(), 0, res);
        return res;
    }

    private void calcCombinations(
            final List<Integer> weights,
            final int remaining,
            final List<Integer> combination,
            final int startAt,
            final LengthSortedResult result
    ) {
        for (int i = startAt; i < weights.size(); i++) {
            final Integer current = weights.get(i);
            if (current > remaining) {
                break;
            }
            combination.add(current);
            if (remaining == current) {
                result.add(combination.toArray(Integer[]::new));
            } else if (remaining > current) {
                calcCombinations(weights, remaining - current, combination, i + 1, result);
            }
            combination.remove(combination.size() - 1);
        }
    }

    private List<Integer> difference(final List<Integer> elements, final Integer[] elementsToRemove) {
        final List<Integer> remaining = new ArrayList<>(elements);
        for (final Integer elementToRemove : elementsToRemove) {
            remaining.remove(elementToRemove);
        }
        return remaining;
    }

    public final long quantumEntanglement(final Integer[] list) {
        long result = list[0];
        for (int i = list.length - 1; i > 0; i--) {
            result *= list[i];
        }
        return result;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
