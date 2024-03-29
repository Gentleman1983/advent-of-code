package de.havox_design.aoc2019.day14;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaceStoichiometry implements AoCFunctionality {
    private static final String ICON_FUEL = "FUEL";
    private static final String ICON_ORE = "ORE";
    private static final char MULTIPLICITY_CHEMICAL_DELIMITER = ' ';
    private static final String PRODUCT_EDUCT_DELIMITER = ", |=> ";

    private final List<String> input;
    private final Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipes;
    private final Map<String, Integer> resultingChemicalCreateAmounts;

    public SpaceStoichiometry(String fileName) {
        input = readData(fileName);
        recipes = parseRecipes();
        resultingChemicalCreateAmounts = new HashMap<>();
        recipes.forEach((key, value) -> resultingChemicalCreateAmounts.put(key.getKey(), key.getValue()));
    }

    public static long processTask1(String fileName) {
        SpaceStoichiometry instance = new SpaceStoichiometry(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SpaceStoichiometry instance = new SpaceStoichiometry(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return processTask1(1L);
    }

    public long processTask1(long neededNumberOfChemicals) {
        Pair<String, Integer> fuel = Pair.of(ICON_FUEL, 1);
        Map<String, Long> spareMaterials = new HashMap<>();

        return oreCost(fuel, recipes, spareMaterials, neededNumberOfChemicals);
    }

    public long processTask2() {
        long multiplier = 1L;

        while(processTask1(multiplier) < 1000000000000L) {
            multiplier += 1000L;
        }

        while(processTask1(multiplier) > 1000000000000L) {
            multiplier--;
        }

        return multiplier;
    }

    private long oreCost(
            Pair<String, Integer> chemical,
            Map<Pair<String, Integer>, List<Pair<String, Integer>>> chemicalMap,
            Map<String, Long> spareMaterials,
            long neededNumberOfChemicals
    ) {
        List<Pair<String, Integer>> chemicalParts = getRecipeEducts(chemicalMap, chemical);
        long oreCount = 0;

        for (Pair<String, Integer> chemicalPart : chemicalParts) {
            String chemicalPartName = chemicalPart.getKey();
            long chemicalPartNeededAmount = chemicalPart.getValue() * neededNumberOfChemicals;

            spareMaterials.putIfAbsent(chemicalPartName, 0L);

            long storedAmount = spareMaterials.get(chemicalPartName);
            long chemicalPartNeededAmountAfterUsingStorage = Math.max(0, chemicalPartNeededAmount - storedAmount);

            storedAmount = Math.max(0, storedAmount - chemicalPartNeededAmount);
            spareMaterials.put(chemicalPartName, storedAmount);

            int chemPartCreatedAmount = resultingChemicalCreateAmounts.get(chemicalPartName);

            if (chemicalPartNeededAmountAfterUsingStorage == 0) {
                continue;
            }

            long amountToCreate =
                    (chemicalPartNeededAmountAfterUsingStorage + chemPartCreatedAmount - 1) / chemPartCreatedAmount;
            long restAmount = (chemPartCreatedAmount * amountToCreate) - chemicalPartNeededAmountAfterUsingStorage;

            if (isBaseChemical(chemicalPart)) {
                int oreCostPerCreate = getRecipeEducts(chemicalMap, chemicalPart).getFirst().getValue();
                long usedOre = oreCostPerCreate * amountToCreate;

                spareMaterials.put(chemicalPartName, spareMaterials.get(chemicalPartName) + restAmount);
                oreCount += usedOre;
            } else {
                spareMaterials.put(chemicalPartName, spareMaterials.get(chemicalPartName) + restAmount);
                oreCount += oreCost(chemicalPart, chemicalMap, spareMaterials, amountToCreate);
            }
        }

        return oreCount;
    }

    private boolean isBaseChemical(Pair<String, Integer> chemical) {
        return containsChemical(recipes, chemical) &&
                hasOnlyASingleEduct(recipes, chemical) &&
                firstEductIsOre(recipes, chemical);
    }

    private boolean containsChemical(
            Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipes,
            Pair<String, Integer> chemical
    ) {
        return recipes
                .keySet()
                .stream()
                .anyMatch(key -> compairKeys(key, chemical));
    }

    private boolean compairKeys(Pair<String, Integer> chemicalA, Pair<String, Integer> chemicalB) {
        return chemicalA.getKey().equals(chemicalB.getKey());
    }

    private List<Pair<String, Integer>> getRecipeEducts(
            Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipes,
            Pair<String, Integer> chemical
    ) {
        return recipes
                .entrySet()
                .stream()
                .filter(entry -> compairKeys(entry.getKey(), chemical))
                .findFirst()
                .orElseThrow()
                .getValue();
    }

    private boolean hasOnlyASingleEduct(
            Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipes,
            Pair<String, Integer> chemical
    ) {
        return getRecipeEducts(recipes, chemical).size() == 1;
    }

    private boolean firstEductIsOre(
            Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipes,
            Pair<String, Integer> chemical
    ) {
        return getRecipeEducts(recipes, chemical)
                .getFirst()
                .getKey()
                .equals(ICON_ORE);
    }

    private Map<Pair<String, Integer>, List<Pair<String, Integer>>> parseRecipes() {
        Map<Pair<String, Integer>, List<Pair<String, Integer>>> recipeMap = new HashMap<>();

        for (String recipeData : input) {
            String[] reactionData = recipeData.split(PRODUCT_EDUCT_DELIMITER);
            String result = reactionData[reactionData.length - 1].trim();
            Pair<String, Integer> resultingReaction = Pair.of(result.substring(
                            result.indexOf(MULTIPLICITY_CHEMICAL_DELIMITER) + 1),
                    Integer.parseInt(result.substring(0, result.indexOf(MULTIPLICITY_CHEMICAL_DELIMITER)))
            );
            List<Pair<String, Integer>> reactionComponents = parseReactionComponents(reactionData);

            recipeMap.put(resultingReaction, reactionComponents);
        }

        return recipeMap;
    }

    private static List<Pair<String, Integer>> parseReactionComponents(String[] reactionData) {
        List<Pair<String, Integer>> reactionComponents = new ArrayList<>();

        for (int i = 0; i < reactionData.length - 1; i++) {
            String reaction = reactionData[i];

            reaction = reaction.trim();

            Pair<String, Integer> reactionPair = Pair.of(
                    reaction.substring(reaction.indexOf(MULTIPLICITY_CHEMICAL_DELIMITER) + 1),
                    Integer.parseInt(reaction.substring(0, reaction.indexOf(MULTIPLICITY_CHEMICAL_DELIMITER)))
            );

            reactionComponents.add(reactionPair);
        }
        return reactionComponents;
    }
}
