package de.havox_design.aoc2019.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniversalOrbitMap implements AoCFunctionality {
    public static final Pattern ORBIT_RELATIONSHIP_PATTERN = Pattern.compile("(.+)\\)(.+)");

    private static final Map<String, ObjectInSpace> UNIVERSAL_ORBIT_MAP = new HashMap<>();

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
        return UNIVERSAL_ORBIT_MAP
                .values()
                .stream()
                .flatMap(ObjectInSpace::toCenterOfMass)
                .count();
    }

    public long processTask2() {
        ObjectInSpace youObject = findObjectInSpace("YOU");
        ObjectInSpace sanObject = findObjectInSpace("SAN");
        List<ObjectInSpace> toCenterPathYou = youObject
                .toCenterOfMass()
                .toList();
        List<ObjectInSpace> toCenterPathSan = sanObject
                .toCenterOfMass()
                .toList();
        ObjectInSpace intersectionObject = toCenterPathYou
                .stream()
                .filter(toCenterPathSan::contains)
                .findFirst()
                .orElseThrow();
        int stepsYou = toCenterPathYou
                .indexOf(intersectionObject);
        int stepsSan = toCenterPathSan
                .indexOf(intersectionObject);

        return (long) stepsYou + stepsSan;
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
        return UNIVERSAL_ORBIT_MAP.computeIfAbsent(objectName, ObjectInSpace::new);
    }

    private ObjectInSpace findObjectInSpace(String name) {
        return UNIVERSAL_ORBIT_MAP.get(name);
    }
}
