package de.havox_design.aoc2018.day24;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GroupParser {
    private static final String GROUP_PATTERN = "^(?<units>\\d+) units each with (?<hitpoints>\\d+) hit points " +
            "(\\((?<resistances>.+)\\) )?with an attack that does (?<damage>\\d+) (?<damageType>\\w+) damage " +
            "at initiative (?<initiative>\\d+)$";
    private static final String IDENTIFIER_IMMUNITIES = "immune to ";
    private static final String IDENTIFIER_WEAKNESSES = "weak to ";
    private static final String LIST_SEPARATOR = ", ";
    private static final String MATCHER_GROUP_DAMAGE = "damage";
    private static final String MATCHER_GROUP_DAMAGE_TYPE = "damageType";
    private static final String MATCHER_GROUP_INITIATIVE = "initiative";
    private static final String MATCHER_GROUP_NUMBER_OF_HITPOINTS = "hitpoints";
    private static final String MATCHER_GROUP_NUMBER_OF_UNITS = "units";
    private static final String MATCHER_GROUP_RESISTANCES = "resistances";
    private static final String RESISTANCE_SEPARATOR = "; ";

    private static int groupNumber = 1;

    public Group parse(String input, String armyName) {
        Pattern pattern = Pattern.compile(GROUP_PATTERN);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            int id = groupNumber;
            String groupName = String.format("%s group #%s", armyName, id);
            int numberOfUnits = Integer.parseInt(matcher.group(MATCHER_GROUP_NUMBER_OF_UNITS));
            int hitpoints = Integer.parseInt(matcher.group(MATCHER_GROUP_NUMBER_OF_HITPOINTS));
            int damage = Integer.parseInt(matcher.group(MATCHER_GROUP_DAMAGE));
            String damageType = matcher.group(MATCHER_GROUP_DAMAGE_TYPE);
            int initiative = Integer.parseInt(matcher.group(MATCHER_GROUP_INITIATIVE));
            String resistances = matcher.group(MATCHER_GROUP_RESISTANCES);
            Set<String> weaknesses = splitList(resistances, IDENTIFIER_WEAKNESSES);
            Set<String> immunities = splitList(resistances, IDENTIFIER_IMMUNITIES);

            incrementGroupNumber();
            return new Group(groupName, id, numberOfUnits, hitpoints, initiative, weaknesses, immunities, damageType, damage);
        }
        throw new IllegalArgumentException("Expected the input '" + input + "' to match pattern '" + GROUP_PATTERN + "'.");
    }

    private Set<String> splitList(String input, String identifier) {
        if(StringUtils.isAllBlank(input)) {
            return Collections.emptySet();
        }

        List<String> resistances = Arrays
                .stream(input.split(RESISTANCE_SEPARATOR))
                .filter(list -> list.startsWith(identifier))
                .toList();

        if(resistances.size() > 1) {
            throw new IllegalArgumentException(String.format("Cannot parse resistance string '%s'.", input));
        }
        else if(resistances.size() == 1) {
            String resistanceList = resistances.getFirst().substring(identifier.length());

            return Arrays
                    .stream(resistanceList.split(LIST_SEPARATOR))
                    .collect(Collectors.toSet());
        }
        else {
            return Collections.emptySet();
        }
    }

    private static void incrementGroupNumber() {
        groupNumber++;
    }
}
