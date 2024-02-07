package de.havox_design.aoc2018.day08;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.List;

public class MemoryManeuver implements AoCFunctionality {
    private static final String VALUE_DELIMITER = " ";
    private static final int INDEX_CHILDREN = 0;
    private static final int INDEX_METADATA = 1;

    private final List<Integer> input;

    public MemoryManeuver(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Integer.parseInt(value.trim()))
                .toList();
    }

    public static int processTask1(String fileName) {
        MemoryManeuver instance = new MemoryManeuver(fileName);
        return instance.processTask1();
    }

    public static int processTask2(String fileName) {
        MemoryManeuver instance = new MemoryManeuver(fileName);
        return instance.processTask2();
    }

    public int processTask1() {
        Node root = buildTree(input);
        return countMetaData(root);
    }

    public int processTask2() {
        Node root = buildTree(input);
        return calculateNodeValue(root);
    }

    private Node buildTree(List<Integer> data) {
        Node node = new Node();
        int numberOfChildren = data.get(INDEX_CHILDREN);
        int numberOfMetaData = data.get(INDEX_METADATA);
        int processedValues = 2;
        List<Integer> content = data.subList(processedValues, data.size());

        for (int i = 0; i < numberOfChildren; i++) {
            Node childNode = buildTree(content);
            int size = childNode.getSize();

            processedValues += size;
            content = content.subList(size, content.size());
            node.addChild(childNode);
        }

        node.setMetaData(data.subList(processedValues, processedValues + numberOfMetaData));
        processedValues += numberOfMetaData;
        node.setSize(processedValues);

        return node;
    }

    private int countMetaData(Node node) {
        int sum = node
                .getMetaData()
                .stream()
                .mapToInt(i -> i)
                .sum();

        for (Node child : node.getChildren()) {
            sum += countMetaData(child);
        }

        return sum;
    }

    private int calculateNodeValue(Node node) {
        int value;

        if (node.getChildren().isEmpty()) {
            value = node
                    .getMetaData()
                    .stream()
                    .mapToInt(i -> i)
                    .sum();
        } else {
            value = node
                    .getMetaData()
                    .stream()
                    .filter(metaDate -> metaDate <= node.getChildren().size())
                    .mapToInt(metaDate -> calculateNodeValue(node.getChildren().get(metaDate - 1)))
                    .sum();
        }

        return value;
    }
}
