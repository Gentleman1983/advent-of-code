package de.havox_design.aoc2019.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniversalOrbitMap implements AoCFunctionality {
    public static final Pattern ORBIT_RELATIONSHIP_PATTERN = Pattern.compile("(.+)\\)(.+)");

    private final Map<String, ObjectInSpace> universalOrbitMap = new HashMap<>();

    public UniversalOrbitMap(String fileName) {
        readData(fileName)
                .forEach(this::putOrbitRelationship);
    }

    public static long processTask1(String fileName) {
        UniversalOrbitMap instance = new UniversalOrbitMap(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        UniversalOrbitMap instance = new UniversalOrbitMap(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return universalOrbitMap
                .values()
                .stream()
                .flatMap(ObjectInSpace::toCenterOfMass)
                .count();
    }

    public long processTask2() {
        return 0;
    }

    private void putOrbitRelationship(String line) {
        Matcher matcher = ORBIT_RELATIONSHIP_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(line + " must match pattern: " + ORBIT_RELATIONSHIP_PATTERN);
        }

        String centerObjectName = matcher.group(1);
        ObjectInSpace centerObject = putObjectInSpace(centerObjectName);

        String orbitObjectName = matcher.group(2);
        ObjectInSpace orbitObject = putObjectInSpace(orbitObjectName);
        orbitObject.setCenterObject(centerObject);
    }

    private ObjectInSpace putObjectInSpace(String objectName) {
        return universalOrbitMap.computeIfAbsent(objectName, ObjectInSpace::new);
    }
}
