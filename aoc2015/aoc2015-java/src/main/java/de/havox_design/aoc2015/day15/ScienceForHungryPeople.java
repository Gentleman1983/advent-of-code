package de.havox_design.aoc2015.day15;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScienceForHungryPeople implements AoCFunctionality {
    private static final String DATA_PATTERN = "^(\\w+)\\: capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)$";

    private final List<Ingredient> input;
    private final List<int[]> distributions;

    @SuppressWarnings("javabugs:S6466")
    public ScienceForHungryPeople(String fileName) {
        input = parseData(fileName);
        distributions = cutToPieces(100, input.size());
    }

    public static int solvePart1(String fileName) {
        ScienceForHungryPeople instance = new ScienceForHungryPeople(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        ScienceForHungryPeople instance = new ScienceForHungryPeople(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return distributions
                .stream()
                .mapToInt(d -> scoreOfDistribution(d, input))
                .max()
                .orElseThrow();
    }

    public int solvePart2() {
        return distributions
                .stream()
                .mapToInt(d -> scoreOfDistributionWithCalories(d, input, 500))
                .max()
                .orElseThrow();
    }

    private int scoreOfDistribution(int[] distribution, List<Ingredient> ingredients) {
        int capacity = mix(distribution, ingredients, Ingredient::capacity);
        int durability = mix(distribution, ingredients, Ingredient::durability);
        int flavour = mix(distribution, ingredients, Ingredient::flavor);
        int texture = mix(distribution, ingredients, Ingredient::texture);
        return capacity * durability * flavour * texture;
    }

    private int scoreOfDistributionWithCalories(int[] distribution, List<Ingredient> ingredients, int calories) {
        if (mix(distribution, ingredients, Ingredient::calories) != calories) {
            return 0;
        }
        return scoreOfDistribution(distribution, ingredients);
    }

    private int mix(int[] distribution, List<Ingredient> ingredients, ToIntFunction<Ingredient> getValue) {
        int sum = 0;
        for (int i = 0; i < distribution.length; ++i) {
            sum += distribution[i] * getValue.applyAsInt(ingredients.get(i));
        }
        return Math.max(sum, 0);
    }

    private List<int[]> cutToPieces(int number, int maxPieces) {
        int[] pieces = new int[maxPieces];
        List<int[]> result = new ArrayList<>();
        cutToPieces(pieces, 0, number, result);
        return result;
    }

    private void cutToPieces(int[] pieces, int index, int reduced, List<int[]> result) {
        if (reduced == 0) {
            result.add(Arrays.copyOf(pieces, pieces.length));
        } else {
            if (index == pieces.length - 1) {
                pieces[index] = reduced;
                result.add(Arrays.copyOf(pieces, pieces.length));
            } else {
                for (int i = 0; i <= reduced; ++i) {
                    pieces[index] = i;
                    cutToPieces(pieces, index + 1, reduced - i, result);
                }
            }
            pieces[index] = 0;
        }
    }

    private List<Ingredient> parseData(String fileName) {
        List<String> data = readData(fileName);
        List<Ingredient> ingredient = new ArrayList<>();

        for(String r : data) {
            ingredient.add(parseIngredient(r));
        }

        return ingredient;
    }

    private Ingredient parseIngredient(String input) {
        Pattern pattern = Pattern.compile(DATA_PATTERN);
        Matcher matcher = pattern.matcher(input);

        if(matcher.matches()) {
            return new Ingredient(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6))
            );
        }
        throw new IllegalArgumentException("Expected the input '" + input + "' to match pattern '" + DATA_PATTERN + "'.");
    }
}
