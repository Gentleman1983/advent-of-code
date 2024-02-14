package de.havox_design.aoc.utils.java.model.data.partitions;

import java.util.List;

public interface Partitioner<T> {
    List<List<T>> partition(List<T> toPartition);
}
