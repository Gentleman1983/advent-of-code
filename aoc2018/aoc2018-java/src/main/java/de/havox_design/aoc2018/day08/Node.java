package de.havox_design.aoc2018.day08;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int size;

    private final List<Node> children = new ArrayList<>();
    private List<Integer> metaData = new ArrayList<>();

    public int getNumberOfChildren() {
        return children.size();
    }

    public int getNumberOfMetaData() {
        return metaData.size();
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<Integer> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<Integer> metaData) {
        this.metaData = metaData;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addChild(Node node) {
        children.add(node);
    }
}
