package de.havox_design.aoc2016.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathResult<T> {
    private final T node;
    private final long distance;
    private final boolean target;
    private final PathResult<T> prev;

    private List<T> path;

    public PathResult(T node, long distance, boolean target, PathResult<T> prev) {
        this.node = node;
        this.distance = distance;
        this.target = target;
        this.prev = prev;
    }

    public T getNode() {
        return node;
    }

    public long getDistance() {
        return distance;
    }

    public boolean isTarget() {
        return target;
    }

    public List<T> getPath() {
        if (path == null) {
            List<T> list = new ArrayList<>();

            for (PathResult<T> e = this; e != null; e = e.prev) {
                list.add(e.node);
            }

            Collections.reverse(list);
            path = Collections.unmodifiableList(list);
        }

        return path;
    }
}
