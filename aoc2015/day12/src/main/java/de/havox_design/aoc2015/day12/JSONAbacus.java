package de.havox_design.aoc2015.day12;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JSONAbacus {
    private final String input;

    public JSONAbacus(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        JSONAbacus instance = new JSONAbacus(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        JSONAbacus instance = new JSONAbacus(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return Arrays
                .stream(input.split("[^\\d-]+"))
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    @SuppressWarnings("squid:S2259")
    public int solvePart2() {
        JSONEntity e = readEntity(new CharacterIterator(input));
        return e
                .getIntegersWithoutRed()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private JSONEntity readEntity(CharacterIterator iterator) {
        if (iterator.hasNext()) {
            char c = iterator.next();
            if (c == ',') {
                c = iterator.next();
            }
            if (c == '{') {
                return readObject(iterator);
            } else if (c == '[') {
                return readArray(iterator);
            } else if (c == '"') {
                return readString(iterator);
            } else if (JSONNumber.canBeStartOfANumber(c)) {
                return readNumber(c, iterator);
            } else {
                return null;
            }
        }
        return null;
    }

    private JSONObject readObject(CharacterIterator iterator) {
        JSONObject result = new JSONObject();
        String key = null;
        char current = iterator.current();
        while (iterator.hasNext() && current != '}' && (key = readKey(iterator)) != null) {
            char colon = iterator.next();
            if (colon != ':') {
                throw new IllegalStateException("expected ':' but got '" + colon + "'");
            }
            JSONEntity entity = readEntity(iterator);
            result.put(key, entity);
            current = iterator.current();
        }
        if (current == '}') {
            iterator.next();
        }
        return result;
    }

    private String readKey(CharacterIterator iterator) {
        char c = iterator.next();
        if (c == ',') {
            iterator.next();
        }
        c = iterator.next();
        StringBuilder keyBuilder = new StringBuilder();
        while (iterator.hasNext() && c != '"') {
            keyBuilder.append(c);
            c = iterator.next();
        }
        return keyBuilder.toString();
    }

    private JSONArray readArray(CharacterIterator iterator) {
        JSONArray result = new JSONArray();
        JSONEntity entity = null;
        while (iterator.hasNext() && ((entity = readEntity(iterator)) != null)) {
            result.add(entity);
        }
        return result;
    }

    private JSONString readString(CharacterIterator iterator) {
        StringBuilder string = new StringBuilder();
        char c = iterator.next();
        while (iterator.hasNext() && c != '"') {
            string.append(c);
            c = iterator.next();
        }
        return new JSONString(string.toString());
    }

    private JSONNumber readNumber(char startChar, CharacterIterator iterator) {
        StringBuilder numberBuilder = new StringBuilder();
        numberBuilder.append(startChar);
        char c = iterator.next();
        while (iterator.hasNext() && JSONNumber.canBeInnerPartOfANumber(c)) {
            numberBuilder.append(c);
            c = iterator.next();
        }
        if (c == '}' || c == ']') {
            iterator.back();
        }
        String numberLiteral = numberBuilder.toString();
        if (numberLiteral.contains(".")) {
            return new JSONNumber(Double.parseDouble(numberLiteral));
        }
        return new JSONNumber(Integer.parseInt(numberLiteral));
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
