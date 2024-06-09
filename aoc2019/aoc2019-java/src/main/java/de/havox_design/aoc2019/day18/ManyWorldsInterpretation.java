package de.havox_design.aoc2019.day18;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.*;
import java.util.stream.Collectors;

public class ManyWorldsInterpretation implements AoCFunctionality {

    private static final char ICON_ENTRANCE = '@';
    private static final char ICON_WALL = '#';

    private final List<String> input;
    private final Map<Position2d<Integer>, Character> areaMap;

    public ManyWorldsInterpretation(String fileName) {
        input = readData(fileName);
        areaMap = getAreaMap(input);
    }

    public static long processTask1(String fileName) {
        ManyWorldsInterpretation instance = new ManyWorldsInterpretation(fileName);

        return instance.processTask1();
    }

    public long processTask1() {
        return calculateLowestNumberOfSteps();
    }

    private int calculateLowestNumberOfSteps() {
        Map<Character, Map<Character, IncomparablePair<Integer, Integer>>> distanceMapping = distanceMapping(areaMap);
        long firstState = 0;
        Map<Long, Integer> stateStepMapping = new HashMap<>();
        int alphabetLen = distanceMapping.size() - 1;

        firstState = (1L << (alphabetLen * 2));
        stateStepMapping.put(firstState, 0);

        long fullKeyState = Long.parseLong(repeat("1", distanceMapping.size() - 1), 2);
        Queue<Long> stateQueue = new LinkedList<>();
        Queue<Character> keyQueue = new LinkedList<>();

        keyQueue.add(ICON_ENTRANCE);
        stateQueue.add(firstState);

        int lowestStepCount = Integer.MAX_VALUE;

        while (!stateQueue.isEmpty()) {
            long state = stateQueue.poll();
            char currentKey = keyQueue.poll();

            if (((state & fullKeyState) == fullKeyState) && (stateStepMapping.get(state) < lowestStepCount)) {
                lowestStepCount = stateStepMapping.get(state);
            }

            Map<Character, IncomparablePair<Integer, Integer>> distancesToOtherKeys = distanceMapping.get(currentKey);
            Set<Character> reachableKeys = distancesToOtherKeys
                    .entrySet()
                    .stream()
                    .filter(e -> ((state >> (e.getKey() - 97)) & 1) == 0 && ((state & e.getValue().v()) == e.getValue().v()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());

            for (Character reachableKey : reachableKeys) {
                int currentSteps = stateStepMapping.get(state) + distancesToOtherKeys.get(reachableKey).k();
                long currentState = (1L << (reachableKey - 97)) | state;

                currentState &= ~(1L << getBitPos(currentKey, alphabetLen) + alphabetLen);
                currentState |= (1L << getBitPos(reachableKey, alphabetLen) + alphabetLen);

                if (stateStepMapping.containsKey(currentState) && stateStepMapping.get(currentState) > currentSteps) {
                    stateStepMapping.put(currentState, currentSteps);
                } else if (!stateStepMapping.containsKey(currentState)) {
                    stateStepMapping.put(currentState, currentSteps);
                    stateQueue.add(currentState);
                    keyQueue.add(reachableKey);
                }
            }
        }

        return lowestStepCount;
    }

    @SuppressWarnings("squid:S1117")
    private Map<Position2d<Integer>, Character> getAreaMap(List<String> input) {
        Map<Position2d<Integer>, Character> areaMap = new HashMap<>();
        int rowCount = 0;
        int colCount = 0;

        for (String row : input) {
            for (int i = 0; i < row.length(); i++) {
                Position2d<Integer> currentPoint = new Position2d<>(colCount++, rowCount);

                areaMap.put(currentPoint, row.charAt(i));
            }

            colCount = 0;
            rowCount++;
        }

        return areaMap;
    }

    public String repeat(String str, int n) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(str).repeat(Math.max(0, n)));

        return sb.toString();
    }

    private Map<Character, Map<Character, IncomparablePair<Integer, Integer>>> distanceMapping(Map<Position2d<Integer>, Character> areaMap) {
        Map<Character, Map<Character, IncomparablePair<Integer, Integer>>> distanceMapping = new HashMap<>();
        List<Position2d<Integer>> keyPositions = areaMap
                .entrySet()
                .stream()
                .filter(e -> Character.isLowerCase(e.getValue()))
                .map(Map.Entry::getKey)
                .toList();
        Position2d<Integer> startingPos = areaMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == ICON_ENTRANCE).map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        distanceMapping.put(areaMap.get(startingPos), bfs(startingPos, areaMap));
        keyPositions.forEach(k -> distanceMapping.put(areaMap.get(k), bfs(k, areaMap)));

        return distanceMapping;
    }

    private int getBitPos(char key, int alphabetLen) {
        return (key == ICON_ENTRANCE) ? alphabetLen : key - 97;
    }

    @SuppressWarnings("squid:S3776")
    private Map<Character, IncomparablePair<Integer, Integer>> bfs(
            Position2d<Integer> startPos,
            Map<Position2d<Integer>, Character> areaMap
    ) {
        Set<Position2d<Integer>> visited = new HashSet<>();
        Node start = new Node(startPos, 0, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        Map<Character, IncomparablePair<Integer, Integer>> targetMapping = new HashMap<>();

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Position2d<Integer> currentPos = currentNode.pos();
            int requiredKeys = currentNode.keys();
            Position2d<Integer> up = new Position2d<>(currentPos.getX(), currentPos.getY() - 1);
            Position2d<Integer> down = new Position2d<>(currentPos.getX(), currentPos.getY() + 1);
            Position2d<Integer> left = new Position2d<>(currentPos.getX() - 1, currentPos.getY());
            Position2d<Integer> right = new Position2d<>(currentPos.getX() + 1, currentPos.getY());

            visited.add(currentPos);

            char areaUp = areaMap.getOrDefault(up, ICON_WALL);
            char areaDown = areaMap.getOrDefault(down, ICON_WALL);
            char areaLeft = areaMap.getOrDefault(left, ICON_WALL);
            char areaRight = areaMap.getOrDefault(right, ICON_WALL);

            if (areaMap.containsKey(up) && !visited.contains(up) && areaUp != ICON_WALL) {
                int keys = requiredKeys;

                if (Character.isLowerCase(areaUp)) {
                    targetMapping.putIfAbsent(areaUp, new IncomparablePair<>(currentNode.steps() + 1, keys));
                } else if (Character.isUpperCase(areaUp)) {
                    keys = (1 << (Character.toLowerCase(areaUp) - 'a')) | keys;
                }

                Node nodeUp = new Node(up, currentNode.steps() + 1, keys);

                queue.add(nodeUp);
            }

            if (areaMap.containsKey(down) && !visited.contains(down) && areaDown != ICON_WALL) {
                int keys = requiredKeys;

                if (Character.isLowerCase(areaDown)) {
                    targetMapping.putIfAbsent(areaDown, new IncomparablePair<>(currentNode.steps() + 1, keys));
                } else if (Character.isUpperCase(areaDown)) {
                    keys = (1 << (Character.toLowerCase(areaDown) - 'a')) | keys;
                }

                Node nodeDown = new Node(down, currentNode.steps() + 1, keys);

                queue.add(nodeDown);
            }

            if (areaMap.containsKey(left) && !visited.contains(left) && areaLeft != ICON_WALL) {
                int keys = requiredKeys;

                if (Character.isLowerCase(areaLeft)) {
                    targetMapping.putIfAbsent(areaLeft, new IncomparablePair<>(currentNode.steps() + 1, keys));
                } else if (Character.isUpperCase(areaLeft)) {
                    keys = (1 << (Character.toLowerCase(areaLeft)) - 'a') | keys;
                }

                Node nodeLeft = new Node(left, currentNode.steps() + 1, keys);

                queue.add(nodeLeft);
            }

            if (areaMap.containsKey(right) && !visited.contains(right) && areaRight != ICON_WALL) {
                int keys = requiredKeys;

                if (Character.isLowerCase(areaRight)) {
                    targetMapping.putIfAbsent(areaRight, new IncomparablePair<>(currentNode.steps() + 1, keys));
                } else if (Character.isUpperCase(areaRight)) {
                    keys = (1 << (Character.toLowerCase(areaRight) - 'a')) | keys;
                }

                Node nodeRight = new Node(right, currentNode.steps() + 1, keys);

                queue.add(nodeRight);
            }
        }

        return targetMapping;
    }
}
