package de.havox_design.aoc2015.day19;

import static de.havox_design.aoc2015.day22.RPGWizardFight.matchRegex;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicineForRudolphJava implements AoCFunctionality {
    public static final Pattern REGEX = Pattern.compile("(\\w+) => (\\w+)");

    private final Map<String, String> reverseMap;
    private final String molecule;

    public MedicineForRudolphJava(String fileName) {
        List<String> input = readData(fileName);

        reverseMap = readReverseMap(input);
        molecule = input.getLast();
    }

    public static int solvePart2(String fileName) {
        MedicineForRudolphJava instance = new MedicineForRudolphJava(fileName);

        return instance.solvePart2();
    }

    public int solvePart2() {
        String oldMolecule;
        String currentMolecule = molecule;
        Pattern regex = Pattern.compile("(" + String.join("|", reverseMap.keySet()) + ")");
        int stepCount = 0;

        do {
            oldMolecule = currentMolecule;
            Matcher matcher = regex.matcher(oldMolecule);
            StringBuilder buffer = new StringBuilder();

            while (matcher.find()) {
                matcher.appendReplacement(buffer, reverseMap.get(matcher.group()));
                stepCount++;
            }

            matcher.appendTail(buffer);
            currentMolecule = buffer.toString();
        } while (!currentMolecule.equals(oldMolecule));

        return stepCount;
    }

    private Map<String, String> readReverseMap(List<String> input) {
        Map<String, String> reverseMapUnderConstruction = new TreeMap<>();

        for(String line : input) {
            if (line.isEmpty()) {
                break;
            }

            Matcher matcher = matchRegex(REGEX, line);
            reverseMapUnderConstruction.put(StringUtils.reverse(matcher.group(2)), StringUtils.reverse(matcher.group(1)));
        }

        return reverseMapUnderConstruction;
    }
}
