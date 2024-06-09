package de.havox_design.aoc2018.day02;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InventoryManagementSystem implements AoCFunctionality {
    private final List<String> input;

    public InventoryManagementSystem(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        InventoryManagementSystem instance = new InventoryManagementSystem(fileName);

        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        InventoryManagementSystem instance = new InventoryManagementSystem(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        long twos = 0L;
        long threes = 0L;

        for(String line : input) {
            Map<Character, Long> counts = countSymbols(line);

            if (counts.containsValue(2L)) {
                twos++;
            }

            if (counts.containsValue(3L)) {
                threes++;
            }
        }

        return twos * threes;
    }

    public String processTask2() {
        List<String> correctBoxes = findCorrectBoxes();
        StringBuilder commonLetters = new StringBuilder();

        for(int i = 0; i < correctBoxes.get(0).length(); i++) {
            if (correctBoxes.get(0).charAt(i) == correctBoxes.get(1).charAt(i)) {
                commonLetters.append(correctBoxes.get(0).charAt(i));
            }
        }

        return commonLetters.toString();
    }

    private Map<Character, Long> countSymbols(String line) {
        Map<Character, Long> result = new HashMap<>();

        for(char c : line.toCharArray()) {
            long oldCount = result.getOrDefault(c, 0L);
            result.put(c, oldCount + 1);
        }

        return result;
    }

    private List<String> findCorrectBoxes() {
        for(String box : input) {
            Optional<String> otherBox = input
                    .stream()
                    .filter(s -> !s.equals(box))
                    .filter(b -> isOtherBox(box, b))
                    .findAny();

            if (otherBox.isPresent()) {
                return List.of(box, otherBox.get());
            }
        }

        throw new IllegalStateException("No correct boxes found");
    }

    private boolean isOtherBox(String box, String otherBox) {
        int errors = 0;

        for(int i = 0; i < box.length(); i++) {
            if (box.charAt(i) != otherBox.charAt(i)) {
                errors++;
            }
        }

        return errors == 1;
    }
}
